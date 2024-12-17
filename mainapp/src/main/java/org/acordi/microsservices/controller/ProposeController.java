package org.acordi.microsservices.controller;

import org.acordi.microsservices.dto.ProposeRequestDTO;
import org.acordi.microsservices.dto.ProposeResponseDTO;
import org.acordi.microsservices.service.ProposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propose")
public class ProposeController {

    @Autowired
    private ProposeService proposeService;

    @PostMapping("")
    public ResponseEntity<ProposeResponseDTO> create(@RequestBody ProposeRequestDTO requestDTO){
        ProposeResponseDTO responseDTO = proposeService.create(requestDTO);
        return ResponseEntity.status(202).body(responseDTO);
    }
}
