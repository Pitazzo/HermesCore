package es.programahermes.Utilidades;

public class ModiferConverter {

	public static double Scala(double level) {
		if (level > 50) {
			double modifier = (level / 25) - 1;
			return modifier;
		} else {
			if (level < 50) {
				double modifier = 0.0136 * level + 0.3197;
				return modifier;
			} else {
				if (level == 50) {

					return 1;
				} else {
					System.out
							.println("[ERROR EN HERMESCORE] REVISAR DB Y MODIFIERS");
					return 1;
				}
			}

		}

	}


}
