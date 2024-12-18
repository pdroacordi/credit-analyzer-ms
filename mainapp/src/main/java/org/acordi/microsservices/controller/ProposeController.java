package org.acordi.microsservices.controller;

import org.acordi.microsservices.dto.ProposeRequestDTO;
import org.acordi.microsservices.dto.ProposeResponseDTO;
import org.acordi.microsservices.service.ProposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/propose")
public class ProposeController {

    @Autowired
    private ProposeService proposeService;

    @PostMapping("")
    public ResponseEntity<ProposeResponseDTO> create(@RequestBody ProposeRequestDTO requestDTO){
        ProposeResponseDTO responseDTO = proposeService.create(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri())
                .body(responseDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<ProposeResponseDTO>> getProposes(){
        List<ProposeResponseDTO> proposes = proposeService.getProposes();
        return ResponseEntity.ok(proposes);
    }
}
