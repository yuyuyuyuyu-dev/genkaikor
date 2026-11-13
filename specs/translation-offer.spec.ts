import { test as base, chromium, expect } from "@playwright/test";

const test = base.extend({
	context: async ({ baseURL, channel, locale }, use, testInfo) => {
		const context = await chromium.launchPersistentContext(
			testInfo.outputPath("profile"),
			{ baseURL, channel, locale },
		);
		await use(context);
		await context.close();
	},
});

for (const locale of ["ja-JP", "en-US", "fr-FR"]) {
	test.describe(() => {
		test.use({ locale });

		test(`should not offer translation when the browser prefers ${locale}`, async ({
			context,
			page,
		}) => {
			// Arrange
			await page.goto("chrome://translate-internals");
			const app = await context.newPage();

			// Act
			await app.goto("/");

			// Assert
			const translationOffers = page.locator(
				"#tabpanel-init-logs td.init-logs-ui-shown",
			);
			await expect(translationOffers).not.toHaveCount(0);
			expect(await translationOffers.allTextContents()).not.toContain("true");
		});
	});
}
