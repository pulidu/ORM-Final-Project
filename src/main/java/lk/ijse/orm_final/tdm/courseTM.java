package lk.ijse.orm_final.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class courseTM {
    private String id;
    private String programName;
    private String duration;
    private double fee;
}