package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FunkyPower extends AbstractPower {
	
	public static final String POWER_ID = FunkyPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	int cnt = 0;
	
	public FunkyPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FunkyPower.png");
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + (3 - this.cnt) +
				DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
	}
	
	@Override
	public void onCardDraw(AbstractCard card) {
		if (++this.cnt == 3) {
			this.addToTop(new ApplyPowerAction(this.owner, this.owner,
					new ChillPower(this.amount), this.amount, true));
			
			this.cnt = 0;
		}
	}
}