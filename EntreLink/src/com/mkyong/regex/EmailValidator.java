package com.mkyong.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	/*this code was "stolen" from the cs 320 resources page
	 * thank your prof hake for the link
	 * code posted on Mkyong.com
	 * coded uses regular expressions to parse a string and 
	 * compare it to known email websites
	 * text from the website -
	 * "Valid Emails
		1. mkyong@yahoo.com, mkyong-100@yahoo.com, mkyong.100@yahoo.com
		2. mkyong111@mkyong.com, mkyong-100@mkyong.net, mkyong.100@mkyong.com.au
		3. mkyong@1.com, mkyong@gmail.com.com
		4. mkyong+100@gmail.com, mkyong-100@yahoo-test.com
		
		3. Invalid Emails
		1. mkyong – must contains “@” symbol
		2. mkyong@.com.my – tld can not start with dot “.”
		3. mkyong123@gmail.a – “.a” is not a valid tld, last tld must contains at least two characters
		4. mkyong123@.com – tld can not start with dot “.”
		5. mkyong123@.com.com – tld can not start with dot “.”
		6. .mkyong@mkyong.com – email’s first character can not start with dot “.”
		7. mkyong()*@gmail.com – email’s is only allow character, digit, underscore and dash
		8. mkyong@%*.com – email’s tld is only allow character and digit
		9. mkyong..2002@gmail.com – double dots “.” are not allow
		10. mkyong.@gmail.com – email’s last character can not end with dot “.”
		11. mkyong@mkyong@gmail.com – double “@” is not allow
		12. mkyong@gmail.com.1a -email’s tld which has two characters can not contains digit"
		*/
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
