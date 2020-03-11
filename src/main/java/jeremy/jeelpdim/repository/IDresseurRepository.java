package jeremy.jeelpdim.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import jeremy.jeelpdim.DTO.DresseurDTO;

public interface IDresseurRepository {
	
	public static final List<DresseurDTO> dresseurs = new ArrayList<>();
	
	public DresseurDTO findById(Integer id);
	
	public List<DresseurDTO> findAll();
	
	public ResponseEntity deleteOne(Integer id);
	
	public ResponseEntity save();
	
	public ResponseEntity update(Integer id);

}
