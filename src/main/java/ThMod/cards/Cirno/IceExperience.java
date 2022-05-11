package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.IceExperienceAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceExperience extends AbstractCirnoCard {
	
	public static final String ID = IceExperience.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	
	public IceExperience() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.NONE
		);
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new IceExperienceAction(this.upgraded));
	}
	
	public AbstractCard makeCopy() {
		return new IceExperience();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}