package com.samajackun.sunnysummer.i18n.pathlocalize;

import lombok.experimental.UtilityClass;

/**
 * Available variables for the {@linkplain RegexTemplate} output pattern.
 * <table border="1">
 * <tr>
 * <th>variable</td>
 * <th>description</th>
 * <th>example value for locale {@code en}</th>
 * <th>example value for locale {@code en_IR}</th>
 * <th>example value for locale {@code en_IR_GA}</th>
 * </tr>
 * <tr>
 * <td>{@code locale}</td>
 * <td>Locale in its serialized form.</td>
 * <td>en</td>
 * <td>en_IR</td>
 * <td>en_IR_GA</td>
 * </tr>
 * <tr>
 * <td>{@code slash_locale}</td>
 * <td>Locale in its serialized form, with a leading slash if it is not empty.</td>
 * <td>/en</td>
 * <td>/en_IR</td>
 * <td>/en_IR_GA</td>
 * </tr>
 * <tr>
 * <td>{@code underscore_locale}</td>
 * <td>Locale in its serialized form, with a leading underscore if it is not empty.</td>
 * <td>_en</td>
 * <td>_en_IR</td>
 * <td>_en_IR_GA</td>
 * </tr>
 * <tr>
 * <td>{@code language}</td>
 * <td>Language.</td>
 * <td>en</td>
 * <td>en</td>
 * <td>en</td>
 * </tr>
 * <tr>
 * <td>{@code slash_language}</td>
 * <td>Language, with a leading slash if it is not empty.</td>
 * <td>/en</td>
 * <td>/en</td>
 * <td>/en</td>
 * </tr>
 * <tr>
 * <td>{@code underscore_language}</td>
 * <td>Language, with a leading underscore if it is not empty.</td>
 * <td>_en</td>
 * <td>_en</td>
 * <td>_en</td>
 * </tr>
 * <tr>
 * <td>{@code country}</td>
 * <td>Country.</td>
 * <td>(empty string)</td>
 * <td>IR</td>
 * <td>IR</td>
 * </tr>
 * <tr>
 * <td>{@code slash_country}</td>
 * <td>Country, with a leading slash if it is not empty.</td>
 * <td>(empty string)</td>
 * <td>/IR</td>
 * <td>/IR</td>
 * </tr>
 * <tr>
 * <td>{@code underscore_country}</td>
 * <td>Country, with a leading underscore if it is not empty.</td>
 * <td>(empty string)</td>
 * <td>_IR</td>
 * <td>_IR</td>
 * </tr>
 * <tr>
 * <td>{@code variant}</td>
 * <td>Variant.</td>
 * <td>(empty string)</td>
 * <td>(empty string)</td>
 * <td>GA</td>
 * </tr>
 * <tr>
 * <td>{@code slash_variant}</td>
 * <td>Variant, with a leading slash if it is not empty.</td>
 * <td>(empty string)</td>
 * <td>(empty string)</td>
 * <td>/GA</td>
 * </tr>
 * <tr>
 * <td>{@code underscore_variant}</td>
 * <td>Variant, with a leading underscore if it is not empty.</td>
 * <td>(empty string)</td>
 * <td>(empty string)</td>
 * <td>_GA</td>
 * </tr>
 *
 * </table>
 *
 * @author SKN
 */
@UtilityClass
public class LocaleKeys
{
	public static final String SERIAL="locale";

	public static final String SLASH_SERIAL="slash_locale";

	public static final String UNDERSCORE_SERIAL="underscore_locale";

	public static final String LANGUAGE="language";

	public static final String SLASH_LANGUAGE="slash_language";

	public static final String UNDERSCORE_LANGUAGE="underscore_language";

	public static final String COUNTRY="country";

	public static final String SLASH_COUNTRY="slash_country";

	public static final String UNDERSCORE_COUNTRY="underscore_country";

	public static final String VARIANT="variant";

	public static final String SLASH_VARIANT="slash_variant";

	public static final String UNDERSCORE_VARIANT="underscore_variant";

}
