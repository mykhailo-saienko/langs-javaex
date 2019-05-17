package ms.vm.lang.lx.parser;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ms.vm.lang.lx.gen.ObjectLexer;
import ms.vm.lang.lx.gen.ObjectParserBaseListener;

public class CommentRemover {

	public static void remove(ParseTree ctx, TokenStreamRewriter rewriter) {
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new Remover(rewriter), ctx);
	}

	private static class Remover extends ObjectParserBaseListener {
		private final TokenStreamRewriter rewriter;

		public Remover(TokenStreamRewriter rewriter) {
			this.rewriter = rewriter;
		}

		@Override
		public void enterEveryRule(ParserRuleContext ctx) {
			List<Token> ts = getTokens().getHiddenTokensToRight(ctx.getStop().getTokenIndex());
			if (ts == null) {
				return;
			}

			for (Token t : ts) {
				if (t.getType() == ObjectLexer.COMMENT || t.getType() == ObjectLexer.LINE_COMMENT) {
					rewriter.delete(t.getTokenIndex());
				}
			}
		}

		private BufferedTokenStream getTokens() {
			return (BufferedTokenStream) rewriter.getTokenStream();
		}
	}
}
