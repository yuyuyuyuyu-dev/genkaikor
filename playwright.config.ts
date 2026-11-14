import { defineConfig } from "@playwright/test";

const appUrl = "http://localhost:4173";

export default defineConfig({
	testDir: "e2e",
	forbidOnly: !!process.env.CI,
	use: {
		baseURL: appUrl,
		channel: "chrome",
	},
	webServer: {
		command:
			"npm exec --no -- serve --no-clipboard --listen 4173 webApp/build/dist/wasmJs/productionExecutable",
		url: appUrl,
		reuseExistingServer: !process.env.CI,
	},
});
