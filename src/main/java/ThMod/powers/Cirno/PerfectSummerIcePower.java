package ThMod.powers.Cirno;

import ThMod.ThMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class PerfectSummerIcePower extends AbstractPower {
	
	public static final String POWER_ID = "LunaticFairyPower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public PerfectSummerIcePower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = Integer.min(amount, 3);
		
		this.type = PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/LunaticFairyPower.png");
	}
	
	@Override
	public void stackPower(int amount) {
		this.amount += amount;
		this.amount = Integer.min(this.amount, 3);
	}
	
	@Override
	public void updateDescription() { // TODO: 还没太懂这里的逻辑，后面看一下
//		if (this.cnt > 0) {
//			this.description =
//					(
//							DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]
//									+ "," + DESCRIPTIONS[2] + (int) Math.pow(2, this.cnt) + DESCRIPTIONS[3]
//					);
//		} else {
//			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ".");
//		}
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
	}
	
	@Override
	public void atStartOfTurn() {
		assert(this.amount <= 3);
		
		ArrayList<AbstractCard> res = new ArrayList<>();
		
		while (true) {
			ArrayList<AbstractCard> cards = new ArrayList<>();
			for (int i = 0; i < this.amount; i++)
				cards.add(AbstractDungeon.returnTrulyRandomCardInCombat());
			
			boolean bad = false;
			for (int i = 0; i < cards.size(); i++)
				for (int j = i + 1; j < cards.size(); j++)
					bad |= (cards.get(i).type == cards.get(j).type ||
							cards.get(i).rarity == cards.get(j).rarity);
			
			if (!bad) {
				res = cards;
				break;
			}
		}
		
		for (AbstractCard card : res) {
			this.addToBot(new MakeTempCardInHandAction(card));
			ThMod.frostKing();
		}
	}
}