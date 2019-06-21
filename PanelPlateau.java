import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import javax.swing.*;

public class PanelPlateau extends JPanel {
	private int nbJoueur;

	private JLabel fond;
	private String sImage;
	private Controleur ctrl;

	public PanelPlateau(Controleur ctrl) {
		this.ctrl = ctrl;
		this.nbJoueur = this.ctrl.getNbJoueur();
		String sImage;
		if (this.nbJoueur <= 4) {
			sImage = ("./images/fonds/plateau2-4.jpg");
		} else {
			sImage = ("./images/fonds/plateau5-6.jpg");
		}
		this.sImage = sImage;

	}

	public void maj() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getToolkit().getImage(sImage), 0, 0, this);

		int x = 0, y = 0;
		int decX = 0;
		int tailleMax = this.ctrl.getTailleMax();

		if (tailleMax == 8) {
			x = 126;
			y = 72;
		}
		if (tailleMax == 10) {
			x = 55;
			y = 14;
		}
		ArrayList<CaseHexa> alCase = this.ctrl.getCases();
		for (int i = 0; i <= tailleMax; i++) {
			for (int j = 0; j <= tailleMax - Math.abs(tailleMax / 2 - i); j++) {
				for (CaseHexa c : alCase) {
					if (c.getPosX() == i && c.getPosY() == j) {
						BufferedImage image = null;
						BufferedImage img = null;
						String s = "./images/test/";
						if (c instanceof Robot) {
							Robot r = (Robot) c;
							try {

								if (r.getJoueur().getRobots().get(1) == r)
									s += "grosRobot";
								else
									s += "petitRobot";
								s += r.getJoueur().getCouleur() + ".png";
								image = ImageIO.read(new File(s));
								img = rotate(image, ((Math.PI / 3) * r.getDir() + (7 * Math.PI) / 6));
								peindre(img, g2, i, j);
								if (r.getCristal() != null) {
									s = "./images/test/cristal";
									s += r.getCristal().getCouleur() + "Robot.png";
									image = ImageIO.read(new File(s));
									img = rotate(image, ((Math.PI / 3)) * r.getDir() + (7 * Math.PI) / 6);
								}

							} catch (Exception e) {
							}

						}
						if (c instanceof Base) {
							Base r = (Base) c;
							try {
								s += "base";

								s += r.getJoueur().getCouleur() + ".png";
								image = ImageIO.read(new File(s));
								img = rotate(image, ((Math.PI / 3)) + (7 * Math.PI) / 6);

							} catch (Exception e) {
							}

						}
						if (c instanceof Cristal) {
							Cristal r = (Cristal) c;
							try {
								s += "cristal";

								s += r.getCouleur() + ".png";
								image = ImageIO.read(new File(s));
								img = rotate(image, ((Math.PI / 3)) + (7 * Math.PI) / 6);

							} catch (Exception e) {
							}
						}
						peindre(img, g2, i, j);
					}

				}
			}
		}

	}

	public void peindre(Image img, Graphics2D g2, int i, int j) {
		int decX = 0;
		int x = 0;
		int y = 0;
		int tailleMax = this.ctrl.getTailleMax();

		if (tailleMax == 8) {
			x = 126;
			y = 72;
		}
		if (tailleMax == 10) {
			x = 55;
			y = 14;
		}
		if (img != null) {
			if (tailleMax == 10)
				decX = (Math.abs(tailleMax / 2 - i) / 2) * 64;
			if (tailleMax == 8)
				decX = (Math.abs(tailleMax / 2 - i) / 2) * 62;

			if (i % 2 == 0 && tailleMax == 10) {
				decX += 31;
			}
			if (i % 2 == 1 && tailleMax == 8) {
				decX += 31;
			}
			if (tailleMax == 10) {
				g2.drawImage(img, x + j * 64 + decX, y + i * 54, this);
				g2.drawString("" + i + " " + j, x + j * 62 + decX, y + i * 54);
			}
			if (tailleMax == 8) {
				g2.drawImage(img, x + j * 62 + decX, y + i * 54, this);
				g2.drawString("" + i + " " + j, x + j * 62 + decX, y + i * 54);
			}
		}
	}

	public static BufferedImage rotate(final BufferedImage bufferedImage, final double radians) {

		final AffineTransform tx = new AffineTransform();
		tx.rotate(radians, bufferedImage.getWidth() / 2.0, bufferedImage.getHeight() / 2.0);

		final AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(bufferedImage, null);
	}

	/*
	 * public static Image rotation(String image, int angle) throws IOException {
	 * 
	 * // cosinus et sinus de l'angle de rotation double cos = Math.cos(Math.PI *
	 * angle / 180.); double sin = Math.sin(Math.PI * angle / 180.);
	 * 
	 * // Chargement de l'image de départ BufferedImage im = ImageIO.read(new
	 * File(image));
	 * 
	 * // dimension de l'image de départ int L = im.getWidth(), H = im.getHeight();
	 * 
	 * // Centre de l'image de départ int Xc = L / 2, Yc = H / 2;
	 * 
	 * // dimension de la nouvelle image int L2 = (int) (L * Math.abs(cos) + H *
	 * Math.abs(sin)); int H2 = (int) (L * Math.abs(sin) + H * Math.abs(cos));
	 * 
	 * // Centre de la nouvelle image int Xc2 = L2 / 2, Yc2 = H2 / 2;
	 * 
	 * // Création de la nouvelle image BufferedImage imro = new BufferedImage(L2,
	 * H2, BufferedImage.TYPE_INT_ARGB);
	 * 
	 * // pour chaque pixel de la nouvelle image for (int y2 = 0; y2 < H2; y2++) {
	 * for (int x2 = 0; x2 < L2; x2++) {
	 * 
	 * // pixel correspondant dans l'image de départ int x = (int) Math.round(Xc +
	 * (x2 - Xc2) * cos - (y2 - Yc2) * sin); int y = (int) Math.round(Yc + (x2 -
	 * Xc2) * sin + (y2 - Yc2) * cos);
	 * 
	 * // copie de la valeur if (x >= 0 && x < L && y >= 0 && y < H) imro.setRGB(x2,
	 * y2, im.getRGB(x, y)); } }
	 * 
	 * // Sauvegarde la nouvelle image return imro; }
	 */

}