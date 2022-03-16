package com.example.Api.clientVsInstitution;


import com.example.Api.institution.Institution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClientId implements Serializable {

    private Institution institution;
    private String userID;

    @Override
    public int hashCode(){
        return Objects.hash(getInstitution(), getUserID());
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }

        if (getClass() != obj.getClass()){
            return false;
        }
        ClientId other = (ClientId) obj;

        return Objects.equals(getInstitution(), other.getInstitution()) && Objects.equals(getUserID(), other.getUserID());
    }
}
