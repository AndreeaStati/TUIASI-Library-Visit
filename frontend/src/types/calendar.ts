export type DateTime = {
  date: Date;
  hours: number[];
  color:string;
};

export type BlockedDateTime = {
  date: Date;
  hours: number[];
  color: string;
};

export type BookingSummary ={
  totalUsers: number;
  bookingDate: string; 
  startTime: string;   
  endTime: string;      
  bookingId: number;
}
