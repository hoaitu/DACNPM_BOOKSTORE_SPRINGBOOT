package com.ht.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

//gui mail thong tin khach hang sau khi dat hang (tran)
@Controller
public class SendMailController {

	@Autowired
	private JavaMailSender mailSender;

	//lay cac du lieu khach hang nhap trong form checkout
	@PostMapping("/checkout")
	public String sendTotal(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		String name = request.getParameter("receiptName");
		String mail = request.getParameter("receiptMail");
		String phone = request.getParameter("receiptPhone");
		String address = request.getParameter("receiptAddress");
		String note = request.getParameter("note");
		String total = request.getParameter("total");

		//SimpleMailMessage message = new SimpleMailMessage();
		//khoi tao mail gui
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		//noi dung mail gui
		String mailSubject = "Thông tin giao hàng";
		String mailContent = "<p><b>Người nhận: </b>" + name + "</p>";
		mailContent += "<p><b>Email: </b>" + mail + "</p>";
		mailContent += "<p><b>Số điện thoại: </b>" + phone + "</p>";
		mailContent += "<p><b>Địa chỉ: </b>" + address + "</p>";
		mailContent += "<p><b>Ghi chú: </b>" + note + "</p>";
		//mailContent += "<p><b>Thanh toán: </b>" + total+ "</p>"
		mailContent += "<p>Đơn hàng đang tiến hành đóng gói để giao đến bạn.<p>"
				+ "Cảm ơn bạn đã tin tưởng và ủng hộ Writer!</p>";
		mailContent += "<br><h3>Bạn có thể liên hệ với WRITER về đơn hàng bằng các cách sau:</h3>"
				+ "<p>	- Gọi đến Hotline (+800) 123 4567 890</p>"
				+ "<p>	- Gửi mail về địa chỉ: info@bookstore.com</p>" + "<p>Chúc bạn có một ngày mới vui vẻ!</p>";

		mailContent += "<img src='cid:logoImage'/>";
		//gui tu mai hoaitugl@gmail.com voi ten goi WRITER Order
		helper.setFrom("hoaitugl@gmail.com", "WRITER Order");
		//den mail nguoi dung nhap
		helper.setTo(mail);
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		// dung de gui mail
		mailSender.send(message);

		ClassPathResource resource = new ClassPathResource("/static/resource/user/img/logo.png");
		helper.addInline("logoImage", resource);
		//tra ve trang success
		return "success";
	}
}
