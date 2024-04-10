package net.fdxdesarrollos.albums.recovery.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fdxdesarrollos.albums.dto.Mensaje;
import net.fdxdesarrollos.albums.recovery.Dto.ChangePasswordDTO;
import net.fdxdesarrollos.albums.recovery.Dto.EmailValuesDTO;
import net.fdxdesarrollos.albums.recovery.Service.EmailService;


@RestController
@RequestMapping("/recovery")
@CrossOrigin
public class RecoveryController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/send-email")
	public ResponseEntity<Mensaje> sendMailTemplate(@RequestBody EmailValuesDTO dto) {
		return ResponseEntity.ok(emailService.sendMailTemplate(dto));
	}
	
    @PostMapping("/change-pass")
    public ResponseEntity<Mensaje> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        return ResponseEntity.ok(emailService.changePassword(dto));
    }	
}

