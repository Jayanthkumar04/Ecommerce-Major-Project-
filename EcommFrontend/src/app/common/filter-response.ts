import { OrderItems } from "./order-items";

export class FilterResponse {


  constructor(public orderId:number,public email:string,public orderDate:Date,public orderStatus:string,public orderTrackingNum:string,public razorpayPaymentId:string,public deliveryDate:Date,public invoiceUrl:string,public items:OrderItems[])
  {

  }
}
