package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



@Controller
public class SentenceController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	/**
	 * Display a small list of Sentences to the caller:
	 */
	@RequestMapping("/sentence")
	public @ResponseBody String getSentence() {
	  return 
		"<h3>Some Sentences</h3><br/>" +	  
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>"
		;
	}

	
	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		String sentence = "There was a problem assembling the sentence!";
		try{
			sentence =  
				String.format("%s %s %s %s %s.",
					getWord("SUBJECT"),
					getWord("VERB"),
					getWord("ARTICLE"),
					getWord("ADJECTIVE"),
					getWord("NOUN") );			
		} catch ( Exception e ) {
			System.out.println(e);
		}
		return sentence;
	}

	
	/**
	 * Obtain a random word for a given part of speech, where the part 
	 * of speech is indicated by the given service / loadBalancerClient ID:
	 */
	public String getWord(String service) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(service);
		return (new RestTemplate()).getForObject(serviceInstance.getUri(), String.class);
	}

}
