

import java.util.*;


//Only handles questions and sentences
public class Chatbot {
	
	
	Map<String, String> Verbsq = new HashMap<String, String>();
	Map<String, String> Verbs = new HashMap<String, String>();
	Map<String, String> WhWords = new HashMap<String, String>();
	Map<String, String> Nouns = new HashMap<String, String>();
	Map<String, String> Pronouns = new HashMap<String, String>();
	Map<String, String> Greetings = new HashMap<String, String>();
	Map<String, String> PartinigMessages = new HashMap<String, String>();
	
	ArrayList<String> YesNoResponses = new ArrayList<String>();
	ArrayList<String> statementResponses= new ArrayList<String>();
	
	Map<String, String> noisewords= new HashMap<String, String>();
	
	ArrayList<String> keywords;
	boolean bExit = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Chatbot cb = new Chatbot();	
		Scanner scanner = new  Scanner(System.in);
		while(true)
		{
			String request = scanner.nextLine();
			
			System.out.println(cb.GenerateResponse(request));
			if(cb.bExit)
			{
				break;			
			}
		}
		
		
		

		
		//scanner.close();
	}





	private String GenerateResponse(String request) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		keywords= new ArrayList<String>();
		String tokens[] = request.split("\\s");
		
		if(tokens.length > 0)
		{
			if(PartinigMessages.containsKey( tokens[0].toLowerCase()))
			{
				bExit = true;	
				return tokens[0].toLowerCase();				
			}
			else if(Greetings.containsKey( tokens[0].toLowerCase()))
			{					
				return tokens[0].toLowerCase();				
			}
			else if(Verbsq.containsKey( tokens[0].toLowerCase()))
			{					
				//Yes/No type question. Question - Generate yes/no/may be/sure response 
				
				return(YesNoResponses.get(rand.nextInt(YesNoResponses.size())));
			}
			else if(WhWords.containsKey( tokens[0].toLowerCase()))
			{					
				//Wh type question. Question - Generate response by gathering the keywords	
				return("Let me think about it");
			}			
			else // A plain statement - Generate OK/I see etc. 
			{
				return(statementResponses.get(rand.nextInt(statementResponses.size())));	
			}
			
		}		
		else
		{
			return ("I am sorry. I don't get this.");			
		}
		
	}
	
	
	
	
	/////////////////////////////////////////
	//// Constructor	
	private  Chatbot() {
		// TODO Auto-generated method stub
		
		//Populate verbs that can begin a question
		Verbsq.put("is","is" );
		Verbsq.put("are","are" );
		Verbsq.put("am","am" );
		Verbsq.put("will","will" );
		Verbsq.put("shall","shall" );
		Verbsq.put("was","was" );
		Verbsq.put("were","were" );
		Verbsq.put("have","have" );
		Verbsq.put("has","has" );
		Verbsq.put("had","had" );
		Verbsq.put("can","can" );
		Verbsq.put("could","could" );
		Verbsq.put("should","should" );
		Verbsq.put("would","would" );
		Verbsq.put("do","do" );
		Verbsq.put("did","did" );
		
		
		
		/////////////////////////////////////////////////
		
		//Populate Whwords
		WhWords.put("who", "who");
		WhWords.put("what", "what");
		WhWords.put("when", "when");
		WhWords.put("where", "where");
		WhWords.put("how", "how");
		WhWords.put("which", "which");
		
		
		/////////////////////////////////////////////////
		
		//Populate Pronouns 
		Pronouns.put("i", "i");
		Pronouns.put("we", "we");
		Pronouns.put("you", "you");
		Pronouns.put("he", "he");
		Pronouns.put("she", "she");
		Pronouns.put("it", "it");
		Pronouns.put("they", "they");
		
		//////////////////////////////////////////////////////
		
		//Populate Noise words 
		noisewords.put("a", "a");
		noisewords.put("an", "an");
		noisewords.put("the", "the");
		noisewords.put("very", "very");
		noisewords.put("quite", "quite");
		noisewords.put("still", "still");
		noisewords.put("and", "and");
		noisewords.put("but", "but");
		noisewords.put("if", "if");
		noisewords.put("then", "then");
		noisewords.put("whereas", "whereas");
		
		//Populate verbs that can begin a question
		Verbs.put("invest","invest" );
		Verbs.put("make", "make");
		Verbs.put("put", "put");
		Verbs.put("keep", "keep");
		Verbs.put("deposit", "deposit");
		Verbs.put("withdraw", "withdraw");
		Verbs.put("buy", "buy");
		Verbs.put("sell", "sell");
		Verbs.put("purchase", "purchase");
			
				
		//Populate nouns		
		Nouns.put("money", "money");
		Nouns.put("finance", "finance");
		Nouns.put("investment", "investment");
		Nouns.put("bank", "bank");
		Nouns.put("shares", "shares");
		
		PartinigMessages.put("bye","bye");
		PartinigMessages.put("bbye","bbye");
		
		Greetings.put("hi","hi");
		Greetings.put("hey","hey");
		Greetings.put("hello","hello");
		
		//Populate YesNoResponses
		YesNoResponses.add("yes");
		YesNoResponses.add("no");
		YesNoResponses.add("sure");
		YesNoResponses.add("may be");
		
		//Populate Statemnent Responses
		statementResponses.add("OK");
		statementResponses.add("i see");
		statementResponses.add("sure");
		
		

		
		
		
	}

}
