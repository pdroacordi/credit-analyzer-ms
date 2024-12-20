package org.acordi.microsservices.controller;

import org.acordi.microsservices.dto.ProposalRequestDTO;
import org.acordi.microsservices.dto.ProposalResponseDTO;
import org.acordi.microsservices.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/propose")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @PostMapping("")
    public ResponseEntity<ProposalResponseDTO> create(@RequestBody ProposalRequestDTO requestDTO){
        ProposalResponseDTO responseDTO = proposalService.create(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri())
                .body(responseDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<ProposalResponseDTO>> getProposals(){
        List<ProposalResponseDTO> proposals = proposalService.getProposals();
        return ResponseEntity.ok(proposals);
    }
}
