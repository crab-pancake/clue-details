package com.cluedetails;

import lombok.Data;

@Data
public class ClueIdToText
{
	int id;

	String text;
	public ClueIdToText(int id, String text)
	{
		this.text = text;
		this.id = id;
	}
}
