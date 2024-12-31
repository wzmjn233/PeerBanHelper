package com.ghostchu.peerbanhelper.util.rule.matcher;

import com.ghostchu.peerbanhelper.text.Lang;
import com.ghostchu.peerbanhelper.text.TranslationComponent;
import com.ghostchu.peerbanhelper.util.rule.AbstractJsonMatcher;
import com.ghostchu.peerbanhelper.util.rule.MatchResult;
import com.ghostchu.peerbanhelper.util.rule.MatchResultEnum;
import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StringStartsWithMatcher extends AbstractJsonMatcher {
    private static final TranslationComponent nameComponent = new TranslationComponent(Lang.RULE_MATCHER_STRING_STARTS_WITH);
    private final String rule;
    private MatchResult hit = new MatchResult(MatchResultEnum.TRUE, new TranslationComponent("StringStatsWith Hit"));
    private MatchResult miss = new MatchResult(MatchResultEnum.FALSE, new TranslationComponent("StringStatsWith Miss"));

    public StringStartsWithMatcher(JsonObject syntax) {
        super(syntax);
        this.rule = syntax.get("content").getAsString().toLowerCase(Locale.ROOT);
        if (syntax.has("hit")) {
            this.hit = new MatchResult(MatchResultEnum.valueOf(syntax.get("hit").getAsString()), new TranslationComponent("StringStatsWith Hit"));
        }
        if (syntax.has("miss")) {
            this.miss = new MatchResult(MatchResultEnum.valueOf(syntax.get("miss").getAsString()), new TranslationComponent("StringStatsWith Miss"));
        }
    }

    @Override
    public @NotNull MatchResult match0(@NotNull String content) {
        content = content.toLowerCase(Locale.ROOT);
        if (content.startsWith(rule)) {
            return hit;
        } else {
            return miss;
        }
    }


    @Override
    public String matcherIdentifier() {
        return "peerbanhelper:stringstartswithmatcher";
    }

    @Override
    public TranslationComponent matcherName() {
        return nameComponent;
    }

    @Override
    public String metadata() {
        return rule;
    }
}
