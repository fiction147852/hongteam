/**
 * 부모계정 자식 강의 결제
 */

import * as PortOne from "@portone/browser-sdk/v2";
function requestPayment() {
  PortOne.requestPayment({
    storeId: "store-de9fb682-27f5-4a8d-a37b-96c75ee490b3", // 고객사 storeId로 변경해주세요.
    channelKey: "channel-key-21477788-3f9c-4718-a6fb-e5a8428a79f9", // 콘솔 결제 연동 화면에서 채널 연동 시 생성된 채널 키를 입력해주세요.
    paymentId: `payment${crypto.randomUUID()}`,
    orderName: "", // lecture.name
    totalAmount: 1000, //lecture.price
    currency: "CURRENCY_KRW", 
    payMethod: "CARD",
    customer: {
      fullName: "포트원", //parent.name
      phoneNumber: "010-0000-1234", //parent.phone
      email: "test@portone.io", //parent.email
    },
  });
}