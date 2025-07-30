export type UserDto = {
    id_user?: number,
    first_name: string,
    last_name: string,
    email: string,
    phone_number: string
}

export type BookingDto = {
    booking_id?: number,
    user: {
        id: number
    },
    booking_date: string,
    start_time: string,
    end_time: string,
    total_price: number,
    status: string,
    details?: string
}

export type BookingDetailsDto = {
    booking: {
        id: number
    },
    category: {
        id: number
    },
    number_of_users: number
}