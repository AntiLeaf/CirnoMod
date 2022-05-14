package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoAnonymousAction;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.IceCreamMachinePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.IceCream;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.ArrayList;

public class IceCreamMachine extends AbstractCirnoCard {
	
	public static final String ID = IceCreamMachine.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	
	public IceCreamMachine() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);
	}
	
	@Override
	public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
		return !AbstractDungeon.player.hasRelic(IceCream.ID);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new CirnoAnonymousAction(() -> {
			if (p.hasRelic(IceCream.ID))
				AbstractDungeon.effectList.add(new ThoughtBubble(
						AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F,
						cardStrings.EXTENDED_DESCRIPTION[0], true));
			else {
				p.getRelic(IceCream.ID);
				this.addToTop(new ApplyPowerAction(p, p, new IceCreamMachinePower()));
			}
		}));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceCreamMachine();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.isInnate = true;
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}