package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sunbathe extends AbstractCirnoCard {
	
	public static final String ID = "Sunbathe";
	public static final String IMG_PATH = "img/cards/Sunbathe.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	private static final int MOTIVATION_GAIN = 4;
	private static final int UPGRADE_PLUS_MOTIVATION_GAIN = 1;
	private static final int DRAW_CNT = 3;
	private static final int UPGRADE_PLUS_DRAW_CNT = 1;
	private static final int BURN_CNT = 2;
	private static final int UPGRADED_BURN_CNT = 3;
	
	public Sunbathe() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.SELF
		);
		
		this.motivationGain = MOTIVATION_GAIN;
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = DRAW_CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, p, this.block));
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivationGain)));
		this.addToBot(new DrawCardAction(this.magicNumber));
		this.addToBot(new MakeTempCardInHandAction(new Burn(), this.upgraded ? BURN_CNT : UPGRADED_BURN_CNT));
	}
	
	public AbstractCard makeCopy() {
		return new Sunbathe();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.motivationGain += UPGRADE_PLUS_MOTIVATION_GAIN;
			upgradeMagicNumber(UPGRADE_PLUS_DRAW_CNT);
			
			initializeDescription();
		}
	}
}