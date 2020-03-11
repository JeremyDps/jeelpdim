package jeremy.jeelpdim.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DresseurDTO {
	
	private Integer id;
	private String nom, mail, password;
}
