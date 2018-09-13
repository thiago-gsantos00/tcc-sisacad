package br.pucminas.sisacad.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	
	public static String createResponse(String tag, String mensagem) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		return node.put(tag, mensagem).toString();
	}

}
