import type { CategoryItem } from "../types/categoryItem";
import type { DateTime } from "../types/calendar";
import type { FormData } from "../types/formData";
import type { UserDto, BookingDto, BookingDetailsDto } from "../types/dtos";
import axios from "axios";

export async function submitReservation(
  categories: CategoryItem[],
  calendarData: DateTime,
  formData: FormData
) {
  try {
    // 1. Creezi userul
    const userDto: UserDto = {
      first_name: formData.firstName,
      last_name: formData.lastName,
      email: formData.email,
      phone_number: formData.phone,
    };
    const userRes = await axios.post("http://localhost:8080/users", userDto);

    // 2. Creezi rezervarea
    const totalPrice = categories.reduce(
      (sum, category) => sum + category.pricePerPerson * category.numberOfPersons,
      0
    );
    const bookingDto: BookingDto = {
      user: { id: userRes.data.id },
      booking_date: calendarData.date.toISOString().split("T")[0],
      start_time: calendarData.hours[0] + ":00",
      end_time: calendarData.hours[calendarData.hours.length - 1] + ":00",
      total_price: totalPrice,
      status: "paid",
      details: formData.observations,
    };
    const bookingRes = await axios.post("http://localhost:8080/bookings", bookingDto);
    const bookingId = bookingRes.data.id;

    // 3. Creezi lista de BookingDetailsDto
    const bookingDetailsList: BookingDetailsDto[] = categories
      .filter((category) => category.numberOfPersons > 0)
      .map((category) => ({
        booking: { id: bookingId },
        category: { id: category.id },
        number_of_users: category.numberOfPersons,
      }));

    // 4. Trimiți toate detaliile într-un singur PUT
    await axios.put(
      `http://localhost:8080/booking-details/${bookingId}`,
      bookingDetailsList
    );

  } catch (error) {
    console.error("Eroare la trimiterea rezervării:", error);
    throw error;
  }
}


//export async function availableSlots()

export async function getCategories() {
    try {
    const response = await axios.get("http://localhost:8080/categories");
    return response.data;
  } catch (error) {
    console.error("Eroare la getCategories:", error);
    //throw error;
  }
}



