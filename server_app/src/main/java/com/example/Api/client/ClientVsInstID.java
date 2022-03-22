package com.example.Api.client;

import com.example.Api.bank.Bank;
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

public class ClientVsInstID implements Serializable {

    private Bank bank;
    private String userID;

    @Override
    public int hashCode(){
        return Objects.hash(getBank());
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        ClientVsInstID that = (ClientVsInstID) o;

        return Objects.equals(getBank(), that.getBank()) && Objects.equals(getUserID(), that.getUserID());
    }
}
