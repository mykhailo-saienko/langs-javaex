// Generated from JavaHeaderLexer.g4 by ANTLR 4.6

	package ms.vm.lang.javaex.gen;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaHeaderLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ABSTRACT=1, ASSERT=2, BOOLEAN=3, BREAK=4, BYTE=5, CASE=6, CATCH=7, CHAR=8, 
		CLASS=9, CONST=10, CONTINUE=11, DEFAULT=12, DO=13, DOUBLE=14, ELSE=15, 
		ENUM=16, EXTENDS=17, FINAL=18, FINALLY=19, FLOAT=20, FOR=21, IF=22, GOTO=23, 
		IMPLEMENTS=24, IMPORT=25, INSTANCEOF=26, INT=27, INTERFACE=28, LONG=29, 
		NATIVE=30, NEW=31, PACKAGE=32, PRIVATE=33, PROTECTED=34, PUBLIC=35, RETURN=36, 
		SHORT=37, STATIC=38, STRICTFP=39, SUPER=40, SWITCH=41, SYNCHRONIZED=42, 
		THIS=43, THROW=44, THROWS=45, TRANSIENT=46, TRY=47, VOID=48, VOLATILE=49, 
		WHILE=50, LPAREN=51, RPAREN=52, OPEN_BRACE=53, LBRACK=54, RBRACK=55, SEMI=56, 
		COMMA=57, DOT=58, ASSIGN=59, GT=60, LT=61, AND=62, QT=63, MUL=64, Identifier=65, 
		AT=66, WS=67, COMMENT=68, LINE_COMMENT=69, ANOTHER_BRACE=70, CLOSE_BRACE=71, 
		STRING=72, EVERYTHING=73;
	public static final int IN_BRACE_MODE = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "IN_BRACE_MODE"
	};

	public static final String[] ruleNames = {
		"ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", "CHAR", 
		"CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", "ENUM", 
		"EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", "IF", "GOTO", "IMPLEMENTS", 
		"IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", 
		"PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", 
		"SUPER", "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", 
		"TRY", "VOID", "VOLATILE", "WHILE", "LPAREN", "RPAREN", "OPEN_BRACE", 
		"LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "AND", 
		"QT", "MUL", "Identifier", "JavaLetter", "JavaLetterOrDigit", "AT", "WS", 
		"COMMENT", "LINE_COMMENT", "ANOTHER_BRACE", "CLOSE_BRACE", "STRING", "STRINGCHARACTER", 
		"ESCAPESEQUENCE", "OCTALESCAPE", "UNICODEESCAPE", "HEXDIGIT", "OCTALDIGIT", 
		"ZEROTOTHREE", "EVERYTHING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'abstract'", "'assert'", "'boolean'", "'break'", "'byte'", "'case'", 
		"'catch'", "'char'", "'class'", "'const'", "'continue'", "'default'", 
		"'do'", "'double'", "'else'", "'enum'", "'extends'", "'final'", "'finally'", 
		"'float'", "'for'", "'if'", "'goto'", "'implements'", "'import'", "'instanceof'", 
		"'int'", "'interface'", "'long'", "'native'", "'new'", "'package'", "'private'", 
		"'protected'", "'public'", "'return'", "'short'", "'static'", "'strictfp'", 
		"'super'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", 
		"'transient'", "'try'", "'void'", "'volatile'", "'while'", "'('", "')'", 
		null, "'['", "']'", "';'", "','", "'.'", "'='", "'>'", "'<'", "'&'", "'?'", 
		"'*'", null, "'@'", null, null, null, null, "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", 
		"CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", 
		"ENUM", "EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", "IF", "GOTO", "IMPLEMENTS", 
		"IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", 
		"PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", 
		"SUPER", "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", 
		"TRY", "VOID", "VOLATILE", "WHILE", "LPAREN", "RPAREN", "OPEN_BRACE", 
		"LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "AND", 
		"QT", "MUL", "Identifier", "AT", "WS", "COMMENT", "LINE_COMMENT", "ANOTHER_BRACE", 
		"CLOSE_BRACE", "STRING", "EVERYTHING"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JavaHeaderLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JavaHeaderLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 65:
			return JavaLetter_sempred((RuleContext)_localctx, predIndex);
		case 66:
			return JavaLetterOrDigit_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean JavaLetter_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return Character.isJavaIdentifierStart(_input.LA(-1));
		case 1:
			return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean JavaLetterOrDigit_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return Character.isJavaIdentifierPart(_input.LA(-1));
		case 3:
			return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2K\u028c\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&"+
		"\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3"+
		")\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3"+
		"/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;"+
		"\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\7B\u021c\nB\fB\16B\u021f\13"+
		"B\3C\3C\3C\3C\3C\3C\5C\u0227\nC\3D\3D\3D\3D\3D\3D\5D\u022f\nD\3E\3E\3"+
		"F\6F\u0234\nF\rF\16F\u0235\3F\3F\3G\3G\3G\3G\7G\u023e\nG\fG\16G\u0241"+
		"\13G\3G\3G\3G\3G\3G\3H\3H\3H\3H\7H\u024c\nH\fH\16H\u024f\13H\3H\3H\3I"+
		"\3I\3I\3I\3J\3J\3J\3J\3K\3K\7K\u025d\nK\fK\16K\u0260\13K\3K\3K\3L\3L\5"+
		"L\u0266\nL\3M\3M\3M\3M\5M\u026c\nM\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\5"+
		"N\u0279\nN\3O\3O\3O\3O\3O\3O\3O\3P\3P\3Q\3Q\3R\3R\3S\6S\u0289\nS\rS\16"+
		"S\u028a\4\u023f\u028a\2T\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f"+
		"\30\r\32\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64"+
		"\33\66\348\35:\36<\37> @!B\"D#F$H%J&L\'N(P)R*T+V,X-Z.\\/^\60`\61b\62d"+
		"\63f\64h\65j\66l\67n8p9r:t;v<x=z>|?~@\u0080A\u0082B\u0084C\u0086\2\u0088"+
		"\2\u008aD\u008cE\u008eF\u0090G\u0092H\u0094I\u0096J\u0098\2\u009a\2\u009c"+
		"\2\u009e\2\u00a0\2\u00a2\2\u00a4\2\u00a6K\4\2\3\16\6\2&&C\\aac|\4\2\2"+
		"\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7\2&&\62;C\\aac|\5"+
		"\2\13\f\16\17\"\"\4\2\f\f\17\17\4\2$$^^\n\2$$))^^ddhhppttvv\5\2\62;CH"+
		"ch\3\2\629\3\2\62\65\u0290\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2"+
		"\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2"+
		"\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2"+
		"\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2"+
		"\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3"+
		"\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2"+
		"\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2"+
		"R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3"+
		"\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2"+
		"\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2\2r\3\2\2\2\2t\3\2\2\2\2v\3\2\2\2\2"+
		"x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~\3\2\2\2\2\u0080\3\2\2\2\2\u0082\3\2"+
		"\2\2\2\u0084\3\2\2\2\2\u008a\3\2\2\2\2\u008c\3\2\2\2\2\u008e\3\2\2\2\2"+
		"\u0090\3\2\2\2\3\u0092\3\2\2\2\3\u0094\3\2\2\2\3\u0096\3\2\2\2\3\u00a6"+
		"\3\2\2\2\4\u00a8\3\2\2\2\6\u00b1\3\2\2\2\b\u00b8\3\2\2\2\n\u00c0\3\2\2"+
		"\2\f\u00c6\3\2\2\2\16\u00cb\3\2\2\2\20\u00d0\3\2\2\2\22\u00d6\3\2\2\2"+
		"\24\u00db\3\2\2\2\26\u00e1\3\2\2\2\30\u00e7\3\2\2\2\32\u00f0\3\2\2\2\34"+
		"\u00f8\3\2\2\2\36\u00fb\3\2\2\2 \u0102\3\2\2\2\"\u0107\3\2\2\2$\u010c"+
		"\3\2\2\2&\u0114\3\2\2\2(\u011a\3\2\2\2*\u0122\3\2\2\2,\u0128\3\2\2\2."+
		"\u012c\3\2\2\2\60\u012f\3\2\2\2\62\u0134\3\2\2\2\64\u013f\3\2\2\2\66\u0146"+
		"\3\2\2\28\u0151\3\2\2\2:\u0155\3\2\2\2<\u015f\3\2\2\2>\u0164\3\2\2\2@"+
		"\u016b\3\2\2\2B\u016f\3\2\2\2D\u0177\3\2\2\2F\u017f\3\2\2\2H\u0189\3\2"+
		"\2\2J\u0190\3\2\2\2L\u0197\3\2\2\2N\u019d\3\2\2\2P\u01a4\3\2\2\2R\u01ad"+
		"\3\2\2\2T\u01b3\3\2\2\2V\u01ba\3\2\2\2X\u01c7\3\2\2\2Z\u01cc\3\2\2\2\\"+
		"\u01d2\3\2\2\2^\u01d9\3\2\2\2`\u01e3\3\2\2\2b\u01e7\3\2\2\2d\u01ec\3\2"+
		"\2\2f\u01f5\3\2\2\2h\u01fb\3\2\2\2j\u01fd\3\2\2\2l\u01ff\3\2\2\2n\u0203"+
		"\3\2\2\2p\u0205\3\2\2\2r\u0207\3\2\2\2t\u0209\3\2\2\2v\u020b\3\2\2\2x"+
		"\u020d\3\2\2\2z\u020f\3\2\2\2|\u0211\3\2\2\2~\u0213\3\2\2\2\u0080\u0215"+
		"\3\2\2\2\u0082\u0217\3\2\2\2\u0084\u0219\3\2\2\2\u0086\u0226\3\2\2\2\u0088"+
		"\u022e\3\2\2\2\u008a\u0230\3\2\2\2\u008c\u0233\3\2\2\2\u008e\u0239\3\2"+
		"\2\2\u0090\u0247\3\2\2\2\u0092\u0252\3\2\2\2\u0094\u0256\3\2\2\2\u0096"+
		"\u025a\3\2\2\2\u0098\u0265\3\2\2\2\u009a\u026b\3\2\2\2\u009c\u0278\3\2"+
		"\2\2\u009e\u027a\3\2\2\2\u00a0\u0281\3\2\2\2\u00a2\u0283\3\2\2\2\u00a4"+
		"\u0285\3\2\2\2\u00a6\u0288\3\2\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7d\2"+
		"\2\u00aa\u00ab\7u\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae"+
		"\7c\2\2\u00ae\u00af\7e\2\2\u00af\u00b0\7v\2\2\u00b0\5\3\2\2\2\u00b1\u00b2"+
		"\7c\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7u\2\2\u00b4\u00b5\7g\2\2\u00b5"+
		"\u00b6\7t\2\2\u00b6\u00b7\7v\2\2\u00b7\7\3\2\2\2\u00b8\u00b9\7d\2\2\u00b9"+
		"\u00ba\7q\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd\7g\2\2"+
		"\u00bd\u00be\7c\2\2\u00be\u00bf\7p\2\2\u00bf\t\3\2\2\2\u00c0\u00c1\7d"+
		"\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5"+
		"\7m\2\2\u00c5\13\3\2\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8\7{\2\2\u00c8\u00c9"+
		"\7v\2\2\u00c9\u00ca\7g\2\2\u00ca\r\3\2\2\2\u00cb\u00cc\7e\2\2\u00cc\u00cd"+
		"\7c\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7g\2\2\u00cf\17\3\2\2\2\u00d0\u00d1"+
		"\7e\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7e\2\2\u00d4"+
		"\u00d5\7j\2\2\u00d5\21\3\2\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7j\2\2\u00d8"+
		"\u00d9\7c\2\2\u00d9\u00da\7t\2\2\u00da\23\3\2\2\2\u00db\u00dc\7e\2\2\u00dc"+
		"\u00dd\7n\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7u\2\2\u00df\u00e0\7u\2\2"+
		"\u00e0\25\3\2\2\2\u00e1\u00e2\7e\2\2\u00e2\u00e3\7q\2\2\u00e3\u00e4\7"+
		"p\2\2\u00e4\u00e5\7u\2\2\u00e5\u00e6\7v\2\2\u00e6\27\3\2\2\2\u00e7\u00e8"+
		"\7e\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7p\2\2\u00ea\u00eb\7v\2\2\u00eb"+
		"\u00ec\7k\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7g\2\2"+
		"\u00ef\31\3\2\2\2\u00f0\u00f1\7f\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7"+
		"h\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7"+
		"\7v\2\2\u00f7\33\3\2\2\2\u00f8\u00f9\7f\2\2\u00f9\u00fa\7q\2\2\u00fa\35"+
		"\3\2\2\2\u00fb\u00fc\7f\2\2\u00fc\u00fd\7q\2\2\u00fd\u00fe\7w\2\2\u00fe"+
		"\u00ff\7d\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7g\2\2\u0101\37\3\2\2\2\u0102"+
		"\u0103\7g\2\2\u0103\u0104\7n\2\2\u0104\u0105\7u\2\2\u0105\u0106\7g\2\2"+
		"\u0106!\3\2\2\2\u0107\u0108\7g\2\2\u0108\u0109\7p\2\2\u0109\u010a\7w\2"+
		"\2\u010a\u010b\7o\2\2\u010b#\3\2\2\2\u010c\u010d\7g\2\2\u010d\u010e\7"+
		"z\2\2\u010e\u010f\7v\2\2\u010f\u0110\7g\2\2\u0110\u0111\7p\2\2\u0111\u0112"+
		"\7f\2\2\u0112\u0113\7u\2\2\u0113%\3\2\2\2\u0114\u0115\7h\2\2\u0115\u0116"+
		"\7k\2\2\u0116\u0117\7p\2\2\u0117\u0118\7c\2\2\u0118\u0119\7n\2\2\u0119"+
		"\'\3\2\2\2\u011a\u011b\7h\2\2\u011b\u011c\7k\2\2\u011c\u011d\7p\2\2\u011d"+
		"\u011e\7c\2\2\u011e\u011f\7n\2\2\u011f\u0120\7n\2\2\u0120\u0121\7{\2\2"+
		"\u0121)\3\2\2\2\u0122\u0123\7h\2\2\u0123\u0124\7n\2\2\u0124\u0125\7q\2"+
		"\2\u0125\u0126\7c\2\2\u0126\u0127\7v\2\2\u0127+\3\2\2\2\u0128\u0129\7"+
		"h\2\2\u0129\u012a\7q\2\2\u012a\u012b\7t\2\2\u012b-\3\2\2\2\u012c\u012d"+
		"\7k\2\2\u012d\u012e\7h\2\2\u012e/\3\2\2\2\u012f\u0130\7i\2\2\u0130\u0131"+
		"\7q\2\2\u0131\u0132\7v\2\2\u0132\u0133\7q\2\2\u0133\61\3\2\2\2\u0134\u0135"+
		"\7k\2\2\u0135\u0136\7o\2\2\u0136\u0137\7r\2\2\u0137\u0138\7n\2\2\u0138"+
		"\u0139\7g\2\2\u0139\u013a\7o\2\2\u013a\u013b\7g\2\2\u013b\u013c\7p\2\2"+
		"\u013c\u013d\7v\2\2\u013d\u013e\7u\2\2\u013e\63\3\2\2\2\u013f\u0140\7"+
		"k\2\2\u0140\u0141\7o\2\2\u0141\u0142\7r\2\2\u0142\u0143\7q\2\2\u0143\u0144"+
		"\7t\2\2\u0144\u0145\7v\2\2\u0145\65\3\2\2\2\u0146\u0147\7k\2\2\u0147\u0148"+
		"\7p\2\2\u0148\u0149\7u\2\2\u0149\u014a\7v\2\2\u014a\u014b\7c\2\2\u014b"+
		"\u014c\7p\2\2\u014c\u014d\7e\2\2\u014d\u014e\7g\2\2\u014e\u014f\7q\2\2"+
		"\u014f\u0150\7h\2\2\u0150\67\3\2\2\2\u0151\u0152\7k\2\2\u0152\u0153\7"+
		"p\2\2\u0153\u0154\7v\2\2\u01549\3\2\2\2\u0155\u0156\7k\2\2\u0156\u0157"+
		"\7p\2\2\u0157\u0158\7v\2\2\u0158\u0159\7g\2\2\u0159\u015a\7t\2\2\u015a"+
		"\u015b\7h\2\2\u015b\u015c\7c\2\2\u015c\u015d\7e\2\2\u015d\u015e\7g\2\2"+
		"\u015e;\3\2\2\2\u015f\u0160\7n\2\2\u0160\u0161\7q\2\2\u0161\u0162\7p\2"+
		"\2\u0162\u0163\7i\2\2\u0163=\3\2\2\2\u0164\u0165\7p\2\2\u0165\u0166\7"+
		"c\2\2\u0166\u0167\7v\2\2\u0167\u0168\7k\2\2\u0168\u0169\7x\2\2\u0169\u016a"+
		"\7g\2\2\u016a?\3\2\2\2\u016b\u016c\7p\2\2\u016c\u016d\7g\2\2\u016d\u016e"+
		"\7y\2\2\u016eA\3\2\2\2\u016f\u0170\7r\2\2\u0170\u0171\7c\2\2\u0171\u0172"+
		"\7e\2\2\u0172\u0173\7m\2\2\u0173\u0174\7c\2\2\u0174\u0175\7i\2\2\u0175"+
		"\u0176\7g\2\2\u0176C\3\2\2\2\u0177\u0178\7r\2\2\u0178\u0179\7t\2\2\u0179"+
		"\u017a\7k\2\2\u017a\u017b\7x\2\2\u017b\u017c\7c\2\2\u017c\u017d\7v\2\2"+
		"\u017d\u017e\7g\2\2\u017eE\3\2\2\2\u017f\u0180\7r\2\2\u0180\u0181\7t\2"+
		"\2\u0181\u0182\7q\2\2\u0182\u0183\7v\2\2\u0183\u0184\7g\2\2\u0184\u0185"+
		"\7e\2\2\u0185\u0186\7v\2\2\u0186\u0187\7g\2\2\u0187\u0188\7f\2\2\u0188"+
		"G\3\2\2\2\u0189\u018a\7r\2\2\u018a\u018b\7w\2\2\u018b\u018c\7d\2\2\u018c"+
		"\u018d\7n\2\2\u018d\u018e\7k\2\2\u018e\u018f\7e\2\2\u018fI\3\2\2\2\u0190"+
		"\u0191\7t\2\2\u0191\u0192\7g\2\2\u0192\u0193\7v\2\2\u0193\u0194\7w\2\2"+
		"\u0194\u0195\7t\2\2\u0195\u0196\7p\2\2\u0196K\3\2\2\2\u0197\u0198\7u\2"+
		"\2\u0198\u0199\7j\2\2\u0199\u019a\7q\2\2\u019a\u019b\7t\2\2\u019b\u019c"+
		"\7v\2\2\u019cM\3\2\2\2\u019d\u019e\7u\2\2\u019e\u019f\7v\2\2\u019f\u01a0"+
		"\7c\2\2\u01a0\u01a1\7v\2\2\u01a1\u01a2\7k\2\2\u01a2\u01a3\7e\2\2\u01a3"+
		"O\3\2\2\2\u01a4\u01a5\7u\2\2\u01a5\u01a6\7v\2\2\u01a6\u01a7\7t\2\2\u01a7"+
		"\u01a8\7k\2\2\u01a8\u01a9\7e\2\2\u01a9\u01aa\7v\2\2\u01aa\u01ab\7h\2\2"+
		"\u01ab\u01ac\7r\2\2\u01acQ\3\2\2\2\u01ad\u01ae\7u\2\2\u01ae\u01af\7w\2"+
		"\2\u01af\u01b0\7r\2\2\u01b0\u01b1\7g\2\2\u01b1\u01b2\7t\2\2\u01b2S\3\2"+
		"\2\2\u01b3\u01b4\7u\2\2\u01b4\u01b5\7y\2\2\u01b5\u01b6\7k\2\2\u01b6\u01b7"+
		"\7v\2\2\u01b7\u01b8\7e\2\2\u01b8\u01b9\7j\2\2\u01b9U\3\2\2\2\u01ba\u01bb"+
		"\7u\2\2\u01bb\u01bc\7{\2\2\u01bc\u01bd\7p\2\2\u01bd\u01be\7e\2\2\u01be"+
		"\u01bf\7j\2\2\u01bf\u01c0\7t\2\2\u01c0\u01c1\7q\2\2\u01c1\u01c2\7p\2\2"+
		"\u01c2\u01c3\7k\2\2\u01c3\u01c4\7|\2\2\u01c4\u01c5\7g\2\2\u01c5\u01c6"+
		"\7f\2\2\u01c6W\3\2\2\2\u01c7\u01c8\7v\2\2\u01c8\u01c9\7j\2\2\u01c9\u01ca"+
		"\7k\2\2\u01ca\u01cb\7u\2\2\u01cbY\3\2\2\2\u01cc\u01cd\7v\2\2\u01cd\u01ce"+
		"\7j\2\2\u01ce\u01cf\7t\2\2\u01cf\u01d0\7q\2\2\u01d0\u01d1\7y\2\2\u01d1"+
		"[\3\2\2\2\u01d2\u01d3\7v\2\2\u01d3\u01d4\7j\2\2\u01d4\u01d5\7t\2\2\u01d5"+
		"\u01d6\7q\2\2\u01d6\u01d7\7y\2\2\u01d7\u01d8\7u\2\2\u01d8]\3\2\2\2\u01d9"+
		"\u01da\7v\2\2\u01da\u01db\7t\2\2\u01db\u01dc\7c\2\2\u01dc\u01dd\7p\2\2"+
		"\u01dd\u01de\7u\2\2\u01de\u01df\7k\2\2\u01df\u01e0\7g\2\2\u01e0\u01e1"+
		"\7p\2\2\u01e1\u01e2\7v\2\2\u01e2_\3\2\2\2\u01e3\u01e4\7v\2\2\u01e4\u01e5"+
		"\7t\2\2\u01e5\u01e6\7{\2\2\u01e6a\3\2\2\2\u01e7\u01e8\7x\2\2\u01e8\u01e9"+
		"\7q\2\2\u01e9\u01ea\7k\2\2\u01ea\u01eb\7f\2\2\u01ebc\3\2\2\2\u01ec\u01ed"+
		"\7x\2\2\u01ed\u01ee\7q\2\2\u01ee\u01ef\7n\2\2\u01ef\u01f0\7c\2\2\u01f0"+
		"\u01f1\7v\2\2\u01f1\u01f2\7k\2\2\u01f2\u01f3\7n\2\2\u01f3\u01f4\7g\2\2"+
		"\u01f4e\3\2\2\2\u01f5\u01f6\7y\2\2\u01f6\u01f7\7j\2\2\u01f7\u01f8\7k\2"+
		"\2\u01f8\u01f9\7n\2\2\u01f9\u01fa\7g\2\2\u01fag\3\2\2\2\u01fb\u01fc\7"+
		"*\2\2\u01fci\3\2\2\2\u01fd\u01fe\7+\2\2\u01fek\3\2\2\2\u01ff\u0200\7}"+
		"\2\2\u0200\u0201\3\2\2\2\u0201\u0202\b\66\2\2\u0202m\3\2\2\2\u0203\u0204"+
		"\7]\2\2\u0204o\3\2\2\2\u0205\u0206\7_\2\2\u0206q\3\2\2\2\u0207\u0208\7"+
		"=\2\2\u0208s\3\2\2\2\u0209\u020a\7.\2\2\u020au\3\2\2\2\u020b\u020c\7\60"+
		"\2\2\u020cw\3\2\2\2\u020d\u020e\7?\2\2\u020ey\3\2\2\2\u020f\u0210\7@\2"+
		"\2\u0210{\3\2\2\2\u0211\u0212\7>\2\2\u0212}\3\2\2\2\u0213\u0214\7(\2\2"+
		"\u0214\177\3\2\2\2\u0215\u0216\7A\2\2\u0216\u0081\3\2\2\2\u0217\u0218"+
		"\7,\2\2\u0218\u0083\3\2\2\2\u0219\u021d\5\u0086C\2\u021a\u021c\5\u0088"+
		"D\2\u021b\u021a\3\2\2\2\u021c\u021f\3\2\2\2\u021d\u021b\3\2\2\2\u021d"+
		"\u021e\3\2\2\2\u021e\u0085\3\2\2\2\u021f\u021d\3\2\2\2\u0220\u0227\t\2"+
		"\2\2\u0221\u0222\n\3\2\2\u0222\u0227\6C\2\2\u0223\u0224\t\4\2\2\u0224"+
		"\u0225\t\5\2\2\u0225\u0227\6C\3\2\u0226\u0220\3\2\2\2\u0226\u0221\3\2"+
		"\2\2\u0226\u0223\3\2\2\2\u0227\u0087\3\2\2\2\u0228\u022f\t\6\2\2\u0229"+
		"\u022a\n\3\2\2\u022a\u022f\6D\4\2\u022b\u022c\t\4\2\2\u022c\u022d\t\5"+
		"\2\2\u022d\u022f\6D\5\2\u022e\u0228\3\2\2\2\u022e\u0229\3\2\2\2\u022e"+
		"\u022b\3\2\2\2\u022f\u0089\3\2\2\2\u0230\u0231\7B\2\2\u0231\u008b\3\2"+
		"\2\2\u0232\u0234\t\7\2\2\u0233\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235"+
		"\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0238\bF"+
		"\3\2\u0238\u008d\3\2\2\2\u0239\u023a\7\61\2\2\u023a\u023b\7,\2\2\u023b"+
		"\u023f\3\2\2\2\u023c\u023e\13\2\2\2\u023d\u023c\3\2\2\2\u023e\u0241\3"+
		"\2\2\2\u023f\u0240\3\2\2\2\u023f\u023d\3\2\2\2\u0240\u0242\3\2\2\2\u0241"+
		"\u023f\3\2\2\2\u0242\u0243\7,\2\2\u0243\u0244\7\61\2\2\u0244\u0245\3\2"+
		"\2\2\u0245\u0246\bG\3\2\u0246\u008f\3\2\2\2\u0247\u0248\7\61\2\2\u0248"+
		"\u0249\7\61\2\2\u0249\u024d\3\2\2\2\u024a\u024c\n\b\2\2\u024b\u024a\3"+
		"\2\2\2\u024c\u024f\3\2\2\2\u024d\u024b\3\2\2\2\u024d\u024e\3\2\2\2\u024e"+
		"\u0250\3\2\2\2\u024f\u024d\3\2\2\2\u0250\u0251\bH\3\2\u0251\u0091\3\2"+
		"\2\2\u0252\u0253\7}\2\2\u0253\u0254\3\2\2\2\u0254\u0255\bI\2\2\u0255\u0093"+
		"\3\2\2\2\u0256\u0257\7\177\2\2\u0257\u0258\3\2\2\2\u0258\u0259\bJ\4\2"+
		"\u0259\u0095\3\2\2\2\u025a\u025e\7$\2\2\u025b\u025d\5\u0098L\2\u025c\u025b"+
		"\3\2\2\2\u025d\u0260\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f"+
		"\u0261\3\2\2\2\u0260\u025e\3\2\2\2\u0261\u0262\7$\2\2\u0262\u0097\3\2"+
		"\2\2\u0263\u0266\n\t\2\2\u0264\u0266\5\u009aM\2\u0265\u0263\3\2\2\2\u0265"+
		"\u0264\3\2\2\2\u0266\u0099\3\2\2\2\u0267\u0268\7^\2\2\u0268\u026c\t\n"+
		"\2\2\u0269\u026c\5\u009cN\2\u026a\u026c\5\u009eO\2\u026b\u0267\3\2\2\2"+
		"\u026b\u0269\3\2\2\2\u026b\u026a\3\2\2\2\u026c\u009b\3\2\2\2\u026d\u026e"+
		"\7^\2\2\u026e\u0279\5\u00a2Q\2\u026f\u0270\7^\2\2\u0270\u0271\5\u00a2"+
		"Q\2\u0271\u0272\5\u00a2Q\2\u0272\u0279\3\2\2\2\u0273\u0274\7^\2\2\u0274"+
		"\u0275\5\u00a4R\2\u0275\u0276\5\u00a2Q\2\u0276\u0277\5\u00a2Q\2\u0277"+
		"\u0279\3\2\2\2\u0278\u026d\3\2\2\2\u0278\u026f\3\2\2\2\u0278\u0273\3\2"+
		"\2\2\u0279\u009d\3\2\2\2\u027a\u027b\7^\2\2\u027b\u027c\7w\2\2\u027c\u027d"+
		"\5\u00a0P\2\u027d\u027e\5\u00a0P\2\u027e\u027f\5\u00a0P\2\u027f\u0280"+
		"\5\u00a0P\2\u0280\u009f\3\2\2\2\u0281\u0282\t\13\2\2\u0282\u00a1\3\2\2"+
		"\2\u0283\u0284\t\f\2\2\u0284\u00a3\3\2\2\2\u0285\u0286\t\r\2\2\u0286\u00a5"+
		"\3\2\2\2\u0287\u0289\13\2\2\2\u0288\u0287\3\2\2\2\u0289\u028a\3\2\2\2"+
		"\u028a\u028b\3\2\2\2\u028a\u0288\3\2\2\2\u028b\u00a7\3\2\2\2\17\2\3\u021d"+
		"\u0226\u022e\u0235\u023f\u024d\u025e\u0265\u026b\u0278\u028a\5\7\3\2\b"+
		"\2\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}