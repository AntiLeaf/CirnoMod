package ThMod.powers.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MotivationPower extends AbstractPower {
	
	public static final String POWER_ID = "MotivationPower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public MotivationPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/Motivation.png");
	}
	
	@Override
	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void updateDescription() { // TODO
//		if (this.cnt > 0) {
//			this.description =
//					(
//							DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]
//									+ "," + DESCRIPTIONS[2] + (int) Math.pow(2, this.cnt) + DESCRIPTIONS[3]
//					);
//		} else {
//			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ".");
//		}
	}
	
	@Override
	public void onAfterCardPlayed(AbstractCard card) {
		if (card instanceof AbstractCirnoCard && ((AbstractCirnoCard) card).isMotivated) {
			this.flash();
			
			int amount = ((AbstractCirnoCard) card).motivationCost < 0 ?
					this.amount : ((AbstractCirnoCard) card).motivationCost;
			
			this.addToTop(new ReducePowerAction(this.owner, this.owner, "Motivation", amount));
		}
	}
}