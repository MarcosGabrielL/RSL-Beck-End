/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softsaj.redelivre.gibgasVenda.Payment;

/**
 *
 * @author Marcos
 */
        
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPreferenceDTO implements Serializable {
    private String accessToken;
    private List<PreferenceItem> items;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public List<PreferenceItem> getItems() {
		return items;
	}
	public void setItems(List<PreferenceItem> items) {
		this.items = items;
	}
    
    
}
