package acme.features;



public class SpamDetector {

	public boolean containsSpam(String[] split, double strongSpamThreshold, String title) {
		double contador = 0.;
		String text = title.toLowerCase().replaceAll(" ", "");
		for (String palabra:split){
			if(text.contains(palabra.replaceAll(" ", ""))) {
				contador+=1;
			}
		}
		Double ratio = contador / title.split(" ").length;
		
		return ratio > strongSpamThreshold;
	}

}
