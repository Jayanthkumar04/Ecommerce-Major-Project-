export class OrderResponse {

  constructor(public orderId:number,public razorpayId:string,public amount:number,public orderTrackingNum:number){
                  
  }
}
