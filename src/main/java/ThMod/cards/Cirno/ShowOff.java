package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShowOff extends AbstractCirnoCard {
	
	public static final String ID = "ShowOff";
	public static final String IMG_PATH = "img/cards/ShowOff.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int CHILL_GAIN = 1;
	private static final int UPGRADE_PLUS_CHILL_GAIN = 1;
	private static final int MOTIVATION_GAIN = 1;
	private static final int UPGRADE_PLUS_MOTIVATION_GAIN = 1;
	
	public ShowOff() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.BASIC,
			CardTarget.SELF
		);
		
		this.chillGain = this.damage = this.baseDamage = CHILL_GAIN;
		this.motivationGain = this.block = this.baseBlock = MOTIVATION_GAIN;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.chillGain)));
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivationGain)));
	}
	
	public AbstractCard makeCopy() {
		return new ShowOff();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_CHILL_GAIN);
			this.chillGain = this.damage;
			upgradeBlock(UPGRADE_PLUS_MOTIVATION_GAIN);
			this.motivationGain = this.block;
			
			initializeDescription();
		}
	}
}