package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@ConfigurationProperties(prefix="wordConfig")
@RefreshScope
public class LuckyWordController {
	 
	String luckyWord;
	String preamble;
	@Value("${wordConfig.fullStatement}")
	String fullStatement;
	
	@RequestMapping("/lucky-word")
	public String showLuckyWord() {
		return preamble + ": " + luckyWord;
	}

	public String getLuckyWord() {
		return luckyWord;
	}

	public void setLuckyWord(String luckyWord) {
		this.luckyWord = luckyWord;
	}

	public String getPreamble() {
		return preamble;
	}

	public void setPreamble(String preamble) {
		this.preamble = preamble;
	}

	@RequestMapping("/full-statement")
	public String showFullStatement() {
		return fullStatement;
	}
	
}
