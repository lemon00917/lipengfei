package com.cdutcm.tcms.ajy.service;

import java.util.List;

import com.cdutcm.tcms.ajy.DTO.ReportDTO;

public interface ReportDTOService {
   List<ReportDTO> selcetByUserID(Long id);
   
   ReportDTO selcetByRecordId(Long recordId);
}
