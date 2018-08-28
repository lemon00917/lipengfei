package com.cdutcm.tcms.ajy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.ajy.DTO.ReportDTO;
import com.cdutcm.tcms.ajy.enums.ResultEnum;
import com.cdutcm.tcms.ajy.exception.JlyException;
import com.cdutcm.tcms.ajy.mapper.BaseJltzMapper;
import com.cdutcm.tcms.ajy.mapper.RecordMapper;
import com.cdutcm.tcms.ajy.mapper.ReportMapper;
import com.cdutcm.tcms.ajy.model.BaseJltz;
import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;
import com.cdutcm.tcms.ajy.service.BaseJltzService;
import com.cdutcm.tcms.ajy.service.RecordService;
import com.cdutcm.tcms.ajy.service.ReportDTOService;
import com.cdutcm.tcms.ajy.service.ReportService;

@Service
public class ReportDTOServiceImpl implements ReportDTOService {

	@Autowired
	private ReportMapper reportMapper;

	@Autowired
	private BaseJltzMapper baseJltzMapper;

	@Autowired
	private RecordMapper recordMapper;

	@Override
	public List<ReportDTO> selcetByUserID(Long id) {

		// 1.用户id查询出所有的就诊记录
		List<ReportDTO> reportDTOs = new ArrayList<>();
		List<Record> records = recordMapper.selectRecords(id);

		if (records == null) {
			throw new JlyException(ResultEnum.NOT_FIND_RECORD);
		}
		// 2.通过就诊记录生成用户所有的就诊数据
		if (records.size() > 0) {
			for (int i = 0; i < records.size(); i++) {
				// 创建一个体质报告传输对象
				ReportDTO recordDTO = new ReportDTO();
				// 通过就诊记录id查询体质
				Long recordId = records.get(i).getId();
				Report report = reportMapper.selectReportById(recordId);
				// 取出当前体质报告对应的经络体质和 四季养生体质(TODO)
				BaseJltz basejltz = baseJltzMapper.selectById(report.getJltzId());
				// 将查询到的所有数据为出书对象赋值
				recordDTO.setId(report.getRecordId());
				recordDTO.setHealScore(report.getHealthScore());
				recordDTO.setJl(basejltz.getJl());
				recordDTO.setJlzz(basejltz.getJlzz());
				recordDTO.setTzbs(report.getTzbs());
				recordDTO.setTztz(basejltz.getTztz());
				recordDTO.setXl(records.get(i).getXl());
				recordDTO.setTw(records.get(i).getTw());
				recordDTO.setSleepTime(records.get(i).getSleepTime());
				recordDTO.setTzbfb(records.get(i).getTzbfb());
				recordDTO.setGmtCreate(records.get(i).getGmtCreate());
				reportDTOs.add(recordDTO);
			}
		}

		// TODO Auto-generated method stub
		return reportDTOs;
	}

	@Override
	public ReportDTO selcetByRecordId(Long recordId) {
		// 1.用户id查询出所有的就诊记录		
				Record records = recordMapper.selectRecordById(recordId); //根据ID查出record表
				if (records == null) {
					throw new JlyException(ResultEnum.NOT_FIND_RECORD);
				}
			// 创建一个体质报告传输对象
						ReportDTO recordDTO = new ReportDTO();
						// 通过就诊记录id查询体质
						Report report = reportMapper.selectReportById(recordId);
						// 取出当前体质报告对应的经络体质和 四季养生体质(TODO)
						BaseJltz basejltz = baseJltzMapper.selectById(report.getJltzId());
						// 将查询到的所有数据为出书对象赋值
						recordDTO.setId(report.getRecordId());
						recordDTO.setHealScore(report.getHealthScore());
						recordDTO.setJl(basejltz.getJl());
						recordDTO.setJlzz(basejltz.getJlzz());
						recordDTO.setTzbs(report.getTzbs());
						recordDTO.setTztz(basejltz.getTztz());
						recordDTO.setXgjb(basejltz.getXgjb());//相关疾病
						recordDTO.setSw(basejltz.getSw());//食物
						recordDTO.setSyly(basejltz.getSyly());//食药两用
						recordDTO.setTnzlcznr(basejltz.getTnzlcznr());//推拿内容
						recordDTO.setTnzlczpd(basejltz.getTnzlczpd());//推拿频度
						recordDTO.setXl(records.getXl());
						recordDTO.setTw(records.getTw());
						recordDTO.setSleepTime(records.getSleepTime());
						recordDTO.setTzbfb(records.getTzbfb());
						recordDTO.setGmtCreate(records.getGmtCreate());	
				return recordDTO;
	}
}
