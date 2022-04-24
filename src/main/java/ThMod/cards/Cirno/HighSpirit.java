package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HighSpirit extends AbstractCirnoCard {
	
	public static final String ID = "HighSpirit";
	public static final String IMG_PATH = "img/cards/HighSpirit.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW_CNT = 2;
	private static final int UPGRADE_PLUS_DRAW_CNT = 1;
	private static final int MOTIVATION_GAIN = 1;
	
	public HighSpirit() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		this.motivationGain = this.block = this.baseBlock = MOTIVATION_GAIN;
		this.magicNumber = this.baseMagicNumber = DRAW_CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("Motivation") && p.getPower("Motivation").amount > 0) {
			this.addToBot(new DrawCardAction(this.magicNumber));
		}
		else
			this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivationGain)));
	}
	
	public AbstractCard makeCopy() {
		return new HighSpirit();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADE_PLUS_DRAW_CNT);
			initializeDescription();
		}
	}
}