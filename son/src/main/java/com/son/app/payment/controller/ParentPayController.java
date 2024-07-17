package com.son.app.payment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.son.app.payment.service.ParentPayService;
import com.son.app.payment.service.ParentPayVO;
import com.son.app.security.service.CustomUserDetails;
@Controller
public class ParentPayController {
	@Autowired
	ParentPayService parentpayService;

	// 결제할 강의 리스트 
    @GetMapping("parent/pay")
    public String lecturePayList(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
    	Integer parentNumber = principal.getMember().getIdNumber();
		List<ParentPayVO> list = parentpayService.LecturePayList(parentNumber);
		model.addAttribute("payment", list);
        return "member/parent/parent_pay";
    }
    // 결제 콜백 처리
    @PostMapping("/parent/pay/callback")
    public ResponseEntity<?> paymentCallback(@RequestBody Map<String, Object> payload) {
        String paymentId = (String) payload.get("paymentId");
        String status = (String) payload.get("status");
        String chargeNumber = (String) payload.get("chargeNumber");

        if ("SUCCESS".equals(status)) {
            ParentPayVO paymentInfo = new ParentPayVO();
            paymentInfo.setChargeCode(paymentId);
            paymentInfo.setReceiveStatus("완료");
            paymentInfo.setPaymentType("CARD");
            paymentInfo.setChargeNumber(Integer.parseInt(chargeNumber));

            int updatedRows = parentpayService.updatePaymentStatus(paymentInfo);

            if (updatedRows > 0) {
                return ResponseEntity.ok().body(Map.of("message", "Payment processed successfully"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Failed to update payment status"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Payment failed"));
        }
    }
}

