import { OrderItems } from "./order-items";

export class Order {

  constructor(public orderId:number,public orderTrackingNum:string,public totalPrice:number,public orderStatus:string,public dateCreated:Date,public razorPaymentId:string,public items:OrderItems[]){

  }
}
