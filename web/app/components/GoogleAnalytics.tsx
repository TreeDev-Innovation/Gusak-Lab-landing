"use client";

import Script from "next/script";

const GA_MEASUREMENT_ID = process.env.NEXT_PUBLIC_GA_MEASUREMENT_ID;

export default function GoogleAnalytics() {
  if (!GA_MEASUREMENT_ID) {
    return null;
  }

  return (
    <>
      <Script
        src={`https://www.googletagmanager.com/gtag/js?id=${GA_MEASUREMENT_ID}`}
        strategy="afterInteractive"
      />
      <Script id="google-analytics" strategy="afterInteractive">
        {`
          window.dataLayer = window.dataLayer || [];
          function gtag(){dataLayer.push(arguments);}
          gtag('js', new Date());
          gtag('config', '${GA_MEASUREMENT_ID}');
        `}
      </Script>
    </>
  );
}

// Analytics event tracking functions
export const trackEvent = (
  eventName: string,
  parameters?: Record<string, string | number | boolean>
) => {
  if (typeof window !== "undefined" && window.gtag) {
    window.gtag("event", eventName, parameters);
  }
};

// Specific events for your use cases
export const analytics = {
  // Track demo game launch
  trackDemoLaunch: () => {
    trackEvent("demo_launch", {
      content_type: "playable_demo",
      item_name: "Elder Puzzle Scroll Demo",
    });
  },

  // Track deeplink/share page visit
  trackDeeplinkVisit: (source?: string) => {
    trackEvent("deeplink_visit", {
      content_type: "share_link",
      source: source || "direct",
    });
  },

  // Track store button clicks
  trackStoreClick: (store: "app_store" | "google_play") => {
    trackEvent("store_click", {
      store_type: store,
      item_name: "Elder Puzzle Scroll",
    });
  },

  // Track email contact clicks
  trackContactClick: (email: string) => {
    trackEvent("contact_click", {
      contact_type: "email",
      email_address: email,
    });
  },
};

// TypeScript declaration for gtag
declare global {
  interface Window {
    gtag: (
      command: string,
      targetId: string,
      config?: Record<string, unknown>
    ) => void;
    dataLayer: unknown[];
  }
}
