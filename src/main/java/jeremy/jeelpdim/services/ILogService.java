package jeremy.jeelpdim.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jeremy.jeelpdim.DTO.DresseurDTO;

public interface ILogService {
	
	public DresseurDTO findById(Integer id);
	
	public List<DresseurDTO> findAll();
	
	public ResponseEntity deleteOne(Integer id);
	
	public ResponseEntity save();
	
	public ResponseEntity update(Integer id);
	
	
}
