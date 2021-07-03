package com.samajackun.summer.conf.provider.properties;

final class UnicodeUtils
{
	private enum State {
		INITIAL, BACKSLASH, UNICODE_SEQUENCE
	};

	private UnicodeUtils()
	{
	}

	public static String decode(CharSequence src)
		throws UnicodeFormatException
	{
		State state=State.INITIAL;
		StringBuilder stb=new StringBuilder(src.length());
		StringBuilder sequence=new StringBuilder(4);
		int len=src.length();
		for (int i=0; i < len; i++)
		{
			char c=src.charAt(i);
			switch (state)
			{
				case INITIAL:
					if (c == '\\')
					{
						state=State.BACKSLASH;
					}
					else
					{
						stb.append(c);
					}
					break;
				case BACKSLASH:
					switch (c)
					{
						case '\\':
							stb.append('\\');
							state=State.INITIAL;
							break;
						case 'u':
							sequence.setLength(0);
							state=State.UNICODE_SEQUENCE;
							break;
						case 'n':
							stb.append('\n');
							state=State.INITIAL;
							break;
						case 'r':
							stb.append('\r');
							state=State.INITIAL;
							break;
						case 't':
							stb.append('\t');
							state=State.INITIAL;
							break;
						case 'b':
							stb.append('\b');
							state=State.INITIAL;
							break;
						default:
							stb.append(c);
							state=State.INITIAL;
							break;
					}
					break;
				case UNICODE_SEQUENCE:
					if (!isHexDigit(c))
					{
						throw new InvalidUnicodeDigitException(c);
					}
					sequence.append(c);
					if (sequence.length() == 4)
					{
						int digitNumber=Integer.parseInt(sequence.toString(), 16);
						stb.append(String.valueOf((char)digitNumber));
						state=State.INITIAL;
					}
					break;
			}
		}
		switch (state)
		{
			case UNICODE_SEQUENCE:
				throw new IncompleteUnicodeSequenceException(sequence.toString());
			case BACKSLASH:
				throw new IncompleteEscapeSequenceException(src);
			case INITIAL:
				// OK
				break;
		}
		return stb.toString();
	}

	private static boolean isHexDigit(char c)
	{
		char d=Character.toLowerCase(c);
		return (d >= 'a' && d <= 'f') || (Character.isDigit(d));
	}
}
