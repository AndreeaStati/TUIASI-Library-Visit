import type {CategoryItem} from "../types/categoryItem"
import type {DateTime} from "../types/calendar"
import type {FormData} from "../types/formData"
import type {UserDto, BookingDto, BookingDetailsDto} from "../types/dtos"
import axios from "axios"

export async function submitReservation(
    categories: CategoryItem[],
    calendarData: DateTime,
    formData: FormData
){
    const userDto: UserDto = {
        first_name: formData.firstName,
        last_name: formData.lastName,
        email: formData.email,
        phone_number: formData.phone
    }
    const userRes = await axios.post("http://localhost:8080/users",userDto);
    const totalPrice = categories.reduce((sum,category)=>{
        return sum + category.pricePerPerson * category.numberOfPersons;
    }, 0);
    const bookingDto: BookingDto = {
        user: {
            id: userRes.data.id
        },
        booking_date: calendarData.date.toISOString().split("T")[0],
        start_time: calendarData.hours[0] + ":00",
        end_time: calendarData.hours[calendarData.hours.length - 1] + ":00",
        total_price:  totalPrice,
        status: "paid",
        details: formData.observations
    }
    const bookingRes = await axios.post("http://localhost:8080/bookings", bookingDto);
    const bookingId = bookingRes.data.id;

    for (const category of categories) {
        if (category.numberOfPersons > 0) {
            const bookingDetailsDto: BookingDetailsDto = {
            booking: {
                id: bookingId
            },
            category: {
                id: category.id
            },
            number_of_users: category.numberOfPersons
            };

            try {
            await axios.put(
                `http://localhost:8080/booking-details/${bookingId}/${category.id}`,
                bookingDetailsDto
            );
            } catch (error) {
            console.error(`Eroare la salvarea detaliilor pentru categoria ${category.id}:`, error);
            }
        }
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