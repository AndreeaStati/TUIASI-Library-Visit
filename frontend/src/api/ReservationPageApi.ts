import type {CategoryItem} from "../types/categoryItem"
import type {DateTime} from "../types/calendar"
import type {FormData} from "../types/formData"
import type {UserDto} from "../types/dtos"
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
    console.log(userRes);
}