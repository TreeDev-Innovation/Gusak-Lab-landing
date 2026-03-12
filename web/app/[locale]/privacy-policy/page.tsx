import type { Metadata } from "next";

const LOCALES = ["en-US", "uk", "de-DE", "es-ES", "fr-FR", "nl-NL", "pt-PT"] as const;

type Locale = (typeof LOCALES)[number];

type Section = {
  title: string;
  body: string[];
};

type Policy = {
  pageTitle: string;
  lastUpdatedLabel: string;
  lastUpdatedValue: string;
  sections: Section[];
};

const POLICIES: Record<Locale, Policy> = {
  "en-US": {
    pageTitle: "Privacy Policy for Puzzle Scroll",
    lastUpdatedLabel: "Last Updated:",
    lastUpdatedValue: "January 30, 2026",
    sections: [
      {
        title: "1. Introduction",
        body: [
          "Welcome to PuzzleScroll. We value your privacy and are committed to protecting your personal data. This Privacy Policy explains how we collect and use information when you play our puzzle game.",
        ],
      },
      {
        title: "2. Information We Collect",
        body: [
          "We prioritize data minimization. We do not collect or store personal information such as your name, email address, or physical location. However, to provide core game functionality, we collect:",
          "UUID (Universally Unique Identifier): A non-personal identifier stored locally and on our secure servers to facilitate the restoration of consumable purchases and track game progress.",
          "Device Information: Basic technical data (e.g., device model, OS version) required for app stability and performance monitoring.",
        ],
      },
      {
        title: "3. Payments and Consumables",
        body: [
          "Our app offers in-app purchases. All financial transactions are processed securely by Google Play Billing.",
          "We do not have access to your credit card or banking details.",
          "We use your UUID solely to ensure that any purchased items are correctly attributed to your account.",
        ],
      },
      {
        title: "4. Advertising",
        body: [
          "To keep our game free to play, we display advertisements. These third-party ad networks (e.g., AdMob) may use pseudonymous identifiers to serve relevant ads. You can manage your ad preferences through your Android device settings (Google Settings > Ads).",
        ],
      },
      {
        title: "5. Data Retention and Security",
        body: [
          "Local Storage: Your UUID and purchase tokens are stored locally on your device to ensure smooth gameplay without a constant internet connection.",
          "Security: We implement industry-standard security measures to protect the integrity of the UUID data used for payment restoration.",
        ],
      },
      {
        title: "6. Children’s Privacy",
        body: [
          "Our game is designed for a general audience. We do not knowingly collect personal data from children under the age of 13. If you believe we have inadvertently collected such information, please contact us immediately so we can delete it.",
        ],
      },
      {
        title: "7. Changes to This Policy",
        body: [
          "We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.",
        ],
      },
      {
        title: "8. Contact Us",
        body: [
          "If you have any questions about this Privacy Policy, please contact us at:",
          "Email: gusak.lab@gmail.com",
        ],
      },
    ],
  },
  uk: {
    pageTitle: "Політика конфіденційності для Puzzle Scroll",
    lastUpdatedLabel: "Останнє оновлення:",
    lastUpdatedValue: "30 січня 2026",
    sections: [
      {
        title: "1. Вступ",
        body: [
          "Ласкаво просимо до PuzzleScroll. Ми цінуємо вашу приватність і прагнемо захищати ваші персональні дані. Ця Політика конфіденційності пояснює, як ми збираємо та використовуємо інформацію під час гри в нашу головоломку.",
        ],
      },
      {
        title: "2. Яку інформацію ми збираємо",
        body: [
          "Ми дотримуємося принципу мінімізації даних. Ми не збираємо і не зберігаємо персональну інформацію, таку як ім’я, електронна адреса або фізичне місцезнаходження. Однак для забезпечення основної функціональності гри ми збираємо:",
          "UUID (універсальний унікальний ідентифікатор): неперсональний ідентифікатор, який зберігається локально та на наших захищених серверах для відновлення витратних покупок і відстеження прогресу гри.",
          "Інформацію про пристрій: базові технічні дані (наприклад, модель пристрою, версія ОС), необхідні для стабільності застосунку та моніторингу продуктивності.",
        ],
      },
      {
        title: "3. Платежі та витратні предмети",
        body: [
          "Наш застосунок пропонує покупки в застосунку. Усі фінансові транзакції безпечно обробляються через Google Play Billing.",
          "Ми не маємо доступу до даних вашої банківської картки або банківських реквізитів.",
          "Ми використовуємо ваш UUID виключно для того, щоб придбані предмети були правильно прив’язані до вашого облікового запису.",
        ],
      },
      {
        title: "4. Реклама",
        body: [
          "Щоб гра залишалась безкоштовною, ми показуємо рекламу. Ці сторонні рекламні мережі (наприклад, AdMob) можуть використовувати псевдонімні ідентифікатори для показу релевантної реклами. Ви можете керувати рекламними налаштуваннями через налаштування Android (Налаштування Google > Реклама).",
        ],
      },
      {
        title: "5. Зберігання даних і безпека",
        body: [
          "Локальне зберігання: ваш UUID і токени покупок зберігаються локально на вашому пристрої, щоб забезпечити плавний ігровий процес без постійного підключення до інтернету.",
          "Безпека: ми застосовуємо стандартні для індустрії заходи безпеки, щоб захистити цілісність даних UUID, які використовуються для відновлення покупок.",
        ],
      },
      {
        title: "6. Конфіденційність дітей",
        body: [
          "Наша гра призначена для широкої аудиторії. Ми свідомо не збираємо персональні дані дітей віком до 13 років. Якщо ви вважаєте, що ми ненавмисно зібрали таку інформацію, будь ласка, негайно зв’яжіться з нами, і ми видалимо її.",
        ],
      },
      {
        title: "7. Зміни до цієї Політики",
        body: [
          "Час від часу ми можемо оновлювати Політику конфіденційності. Ми повідомимо про зміни, опублікувавши нову версію Політики на цій сторінці.",
        ],
      },
      {
        title: "8. Зв’яжіться з нами",
        body: [
          "Якщо у вас є запитання щодо цієї Політики конфіденційності, будь ласка, напишіть нам:",
          "Email: gusak.lab@gmail.com",
        ],
      },
    ],
  },
  "de-DE": {
    pageTitle: "Datenschutzerklärung für Puzzle Scroll",
    lastUpdatedLabel: "Zuletzt aktualisiert:",
    lastUpdatedValue: "30. Januar 2026",
    sections: [
      {
        title: "1. Einleitung",
        body: [
          "Willkommen bei PuzzleScroll. Wir schätzen Ihre Privatsphäre und verpflichten uns, Ihre personenbezogenen Daten zu schützen. Diese Datenschutzerklärung erläutert, wie wir Informationen erfassen und verwenden, wenn Sie unser Puzzle-Spiel spielen.",
        ],
      },
      {
        title: "2. Welche Informationen wir erfassen",
        body: [
          "Wir setzen auf Datenminimierung. Wir erfassen oder speichern keine personenbezogenen Informationen wie Ihren Namen, Ihre E-Mail-Adresse oder Ihren physischen Standort. Um jedoch die Kernfunktionen des Spiels bereitzustellen, erfassen wir:",
          "UUID (Universally Unique Identifier): Eine nicht personenbezogene Kennung, die lokal und auf unseren sicheren Servern gespeichert wird, um die Wiederherstellung von Verbrauchskäufen zu ermöglichen und den Spielfortschritt zu verfolgen.",
          "Geräteinformationen: Grundlegende technische Daten (z. B. Gerätemodell, Betriebssystemversion), die für App-Stabilität und Leistungsüberwachung erforderlich sind.",
        ],
      },
      {
        title: "3. Zahlungen und Verbrauchsgüter",
        body: [
          "Unsere App bietet In-App-Käufe an. Alle finanziellen Transaktionen werden sicher über Google Play Billing verarbeitet.",
          "Wir haben keinen Zugriff auf Ihre Kreditkarten- oder Bankdaten.",
          "Wir verwenden Ihre UUID ausschließlich, um sicherzustellen, dass gekaufte Artikel korrekt Ihrem Konto zugeordnet werden.",
        ],
      },
      {
        title: "4. Werbung",
        body: [
          "Damit unser Spiel kostenlos bleibt, zeigen wir Werbung an. Diese Drittanbieter-Werbenetzwerke (z. B. AdMob) können pseudonyme Kennungen verwenden, um relevante Anzeigen bereitzustellen. Sie können Ihre Werbeeinstellungen über die Android-Geräteeinstellungen verwalten (Google-Einstellungen > Anzeigen).",
        ],
      },
      {
        title: "5. Datenspeicherung und Sicherheit",
        body: [
          "Lokale Speicherung: Ihre UUID und Kauf-Tokens werden lokal auf Ihrem Gerät gespeichert, um ein reibungsloses Spielerlebnis ohne ständige Internetverbindung zu gewährleisten.",
          "Sicherheit: Wir setzen branchenübliche Sicherheitsmaßnahmen ein, um die Integrität der UUID-Daten zu schützen, die für die Wiederherstellung von Zahlungen verwendet werden.",
        ],
      },
      {
        title: "6. Datenschutz von Kindern",
        body: [
          "Unser Spiel richtet sich an ein allgemeines Publikum. Wir erfassen wissentlich keine personenbezogenen Daten von Kindern unter 13 Jahren. Wenn Sie glauben, dass wir solche Informationen unbeabsichtigt erfasst haben, kontaktieren Sie uns bitte umgehend, damit wir sie löschen können.",
        ],
      },
      {
        title: "7. Änderungen an dieser Richtlinie",
        body: [
          "Wir können unsere Datenschutzerklärung von Zeit zu Zeit aktualisieren. Wir informieren Sie über Änderungen, indem wir die neue Datenschutzerklärung auf dieser Seite veröffentlichen.",
        ],
      },
      {
        title: "8. Kontakt",
        body: [
          "Wenn Sie Fragen zu dieser Datenschutzerklärung haben, kontaktieren Sie uns bitte unter:",
          "E-Mail: gusak.lab@gmail.com",
        ],
      },
    ],
  },
  "es-ES": {
    pageTitle: "Política de Privacidad de Puzzle Scroll",
    lastUpdatedLabel: "Última actualización:",
    lastUpdatedValue: "30 de enero de 2026",
    sections: [
      {
        title: "1. Introducción",
        body: [
          "Bienvenido a PuzzleScroll. Valoramos tu privacidad y estamos comprometidos a proteger tus datos personales. Esta Política de Privacidad explica cómo recopilamos y usamos información cuando juegas a nuestro juego de puzzles.",
        ],
      },
      {
        title: "2. Información que recopilamos",
        body: [
          "Priorizamos la minimización de datos. No recopilamos ni almacenamos información personal como tu nombre, correo electrónico o ubicación física. Sin embargo, para proporcionar la funcionalidad principal del juego, recopilamos:",
          "UUID (Identificador Universal Único): un identificador no personal almacenado localmente y en nuestros servidores seguros para facilitar la restauración de compras consumibles y realizar un seguimiento del progreso del juego.",
          "Información del dispositivo: datos técnicos básicos (por ejemplo, modelo del dispositivo, versión del sistema operativo) necesarios para la estabilidad de la app y el monitoreo del rendimiento.",
        ],
      },
      {
        title: "3. Pagos y consumibles",
        body: [
          "Nuestra app ofrece compras dentro de la aplicación. Todas las transacciones financieras se procesan de forma segura mediante Google Play Billing.",
          "No tenemos acceso a los datos de tu tarjeta de crédito ni a tus datos bancarios.",
          "Usamos tu UUID únicamente para garantizar que los artículos comprados se atribuyan correctamente a tu cuenta.",
        ],
      },
      {
        title: "4. Publicidad",
        body: [
          "Para mantener nuestro juego gratuito, mostramos anuncios. Estas redes publicitarias de terceros (p. ej., AdMob) pueden usar identificadores seudónimos para ofrecer anuncios relevantes. Puedes administrar tus preferencias de anuncios a través de la configuración de tu dispositivo Android (Ajustes de Google > Anuncios).",
        ],
      },
      {
        title: "5. Retención de datos y seguridad",
        body: [
          "Almacenamiento local: tu UUID y los tokens de compra se almacenan localmente en tu dispositivo para garantizar un juego fluido sin una conexión constante a internet.",
          "Seguridad: implementamos medidas de seguridad estándar de la industria para proteger la integridad de los datos UUID usados para la restauración de pagos.",
        ],
      },
      {
        title: "6. Privacidad de los niños",
        body: [
          "Nuestro juego está diseñado para una audiencia general. No recopilamos deliberadamente datos personales de niños menores de 13 años. Si crees que hemos recopilado dicha información de forma inadvertida, contáctanos de inmediato para poder eliminarla.",
        ],
      },
      {
        title: "7. Cambios en esta política",
        body: [
          "Podemos actualizar nuestra Política de Privacidad de vez en cuando. Te notificaremos cualquier cambio publicando la nueva Política de Privacidad en esta página.",
        ],
      },
      {
        title: "8. Contáctanos",
        body: [
          "Si tienes alguna pregunta sobre esta Política de Privacidad, contáctanos en:",
          "Correo electrónico: gusak.lab@gmail.com",
        ],
      },
    ],
  },
  "fr-FR": {
    pageTitle: "Politique de confidentialité de Puzzle Scroll",
    lastUpdatedLabel: "Dernière mise à jour :",
    lastUpdatedValue: "30 janvier 2026",
    sections: [
      {
        title: "1. Introduction",
        body: [
          "Bienvenue sur PuzzleScroll. Nous accordons de l’importance à votre vie privée et nous nous engageons à protéger vos données personnelles. Cette politique de confidentialité explique comment nous collectons et utilisons des informations lorsque vous jouez à notre jeu de réflexion.",
        ],
      },
      {
        title: "2. Informations que nous collectons",
        body: [
          "Nous privilégions la minimisation des données. Nous ne collectons ni ne stockons d’informations personnelles telles que votre nom, votre adresse e-mail ou votre localisation physique. Toutefois, afin de fournir les fonctionnalités essentielles du jeu, nous collectons :",
          "UUID (Identifiant unique universel) : un identifiant non personnel stocké localement et sur nos serveurs sécurisés pour faciliter la restauration des achats consommables et suivre la progression du jeu.",
          "Informations sur l’appareil : données techniques de base (par ex. modèle de l’appareil, version du système d’exploitation) nécessaires à la stabilité de l’application et au suivi des performances.",
        ],
      },
      {
        title: "3. Paiements et consommables",
        body: [
          "Notre application propose des achats intégrés. Toutes les transactions financières sont traitées de manière sécurisée par Google Play Billing.",
          "Nous n’avons pas accès à vos informations de carte bancaire ou à vos coordonnées bancaires.",
          "Nous utilisons votre UUID uniquement pour nous assurer que les articles achetés sont correctement attribués à votre compte.",
        ],
      },
      {
        title: "4. Publicité",
        body: [
          "Pour que notre jeu reste gratuit, nous affichons des publicités. Ces réseaux publicitaires tiers (par ex. AdMob) peuvent utiliser des identifiants pseudonymes afin de diffuser des publicités pertinentes. Vous pouvez gérer vos préférences publicitaires via les paramètres de votre appareil Android (Paramètres Google > Annonces).",
        ],
      },
      {
        title: "5. Conservation des données et sécurité",
        body: [
          "Stockage local : votre UUID et les jetons d’achat sont stockés localement sur votre appareil afin d’assurer une expérience fluide sans connexion Internet constante.",
          "Sécurité : nous mettons en œuvre des mesures de sécurité conformes aux standards de l’industrie pour protéger l’intégrité des données UUID utilisées pour la restauration des paiements.",
        ],
      },
      {
        title: "6. Confidentialité des enfants",
        body: [
          "Notre jeu s’adresse à un public général. Nous ne collectons pas sciemment de données personnelles d’enfants de moins de 13 ans. Si vous pensez que nous avons collecté de telles informations par inadvertance, veuillez nous contacter immédiatement afin que nous puissions les supprimer.",
        ],
      },
      {
        title: "7. Modifications de cette politique",
        body: [
          "Nous pouvons mettre à jour notre politique de confidentialité de temps à autre. Nous vous informerons de tout changement en publiant la nouvelle politique de confidentialité sur cette page.",
        ],
      },
      {
        title: "8. Nous contacter",
        body: [
          "Si vous avez des questions concernant cette politique de confidentialité, veuillez nous contacter à l’adresse suivante :",
          "E-mail : gusak.lab@gmail.com",
        ],
      },
    ],
  },
  "nl-NL": {
    pageTitle: "Privacybeleid voor Puzzle Scroll",
    lastUpdatedLabel: "Laatst bijgewerkt:",
    lastUpdatedValue: "30 januari 2026",
    sections: [
      {
        title: "1. Inleiding",
        body: [
          "Welkom bij PuzzleScroll. Wij hechten veel waarde aan je privacy en zetten ons in om je persoonsgegevens te beschermen. Dit privacybeleid legt uit hoe wij informatie verzamelen en gebruiken wanneer je ons puzzelspel speelt.",
        ],
      },
      {
        title: "2. Informatie die wij verzamelen",
        body: [
          "Wij geven prioriteit aan dataminimalisatie. Wij verzamelen of bewaren geen persoonlijke informatie zoals je naam, e-mailadres of fysieke locatie. Om de kernfunctionaliteit van het spel te kunnen bieden, verzamelen wij echter:",
          "UUID (Universally Unique Identifier): een niet-persoonlijke identifier die lokaal en op onze beveiligde servers wordt opgeslagen om het herstellen van verbruiksaankopen mogelijk te maken en de voortgang van het spel bij te houden.",
          "Apparaatinformatie: basis technische gegevens (bijv. apparaatmodel, OS-versie) die nodig zijn voor app-stabiliteit en prestatiemonitoring.",
        ],
      },
      {
        title: "3. Betalingen en verbruiksartikelen",
        body: [
          "Onze app biedt in-app aankopen. Alle financiële transacties worden veilig verwerkt via Google Play Billing.",
          "Wij hebben geen toegang tot je creditcard- of bankgegevens.",
          "Wij gebruiken je UUID uitsluitend om ervoor te zorgen dat aangekochte items correct aan je account worden gekoppeld.",
        ],
      },
      {
        title: "4. Advertenties",
        body: [
          "Om ons spel gratis te houden, tonen we advertenties. Deze advertentienetwerken van derden (bijv. AdMob) kunnen pseudonieme identifiers gebruiken om relevante advertenties te tonen. Je kunt je advertentievoorkeuren beheren via de instellingen van je Android-apparaat (Google-instellingen > Advertenties).",
        ],
      },
      {
        title: "5. Bewaring van gegevens en beveiliging",
        body: [
          "Lokale opslag: je UUID en aankoop-tokens worden lokaal op je apparaat opgeslagen om soepel te kunnen spelen zonder constante internetverbinding.",
          "Beveiliging: wij nemen beveiligingsmaatregelen volgens de industrienormen om de integriteit van de UUID-gegevens te beschermen die worden gebruikt voor het herstellen van betalingen.",
        ],
      },
      {
        title: "6. Privacy van kinderen",
        body: [
          "Ons spel is bedoeld voor een algemeen publiek. Wij verzamelen niet bewust persoonsgegevens van kinderen jonger dan 13 jaar. Als je denkt dat wij dergelijke informatie onbedoeld hebben verzameld, neem dan onmiddellijk contact met ons op zodat wij deze kunnen verwijderen.",
        ],
      },
      {
        title: "7. Wijzigingen in dit beleid",
        body: [
          "Wij kunnen ons privacybeleid van tijd tot tijd bijwerken. We zullen je op de hoogte stellen van eventuele wijzigingen door het nieuwe privacybeleid op deze pagina te plaatsen.",
        ],
      },
      {
        title: "8. Contact",
        body: [
          "Als je vragen hebt over dit privacybeleid, neem dan contact met ons op via:",
          "E-mail: gusak.lab@gmail.com",
        ],
      },
    ],
  },
  "pt-PT": {
    pageTitle: "Política de Privacidade do Puzzle Scroll",
    lastUpdatedLabel: "Última atualização:",
    lastUpdatedValue: "30 de janeiro de 2026",
    sections: [
      {
        title: "1. Introdução",
        body: [
          "Bem-vindo ao PuzzleScroll. Valorizamos a sua privacidade e estamos comprometidos em proteger os seus dados pessoais. Esta Política de Privacidade explica como recolhemos e utilizamos informações quando joga o nosso jogo de puzzles.",
        ],
      },
      {
        title: "2. Informações que recolhemos",
        body: [
          "Damos prioridade à minimização de dados. Não recolhemos nem armazenamos informações pessoais, como o seu nome, endereço de e-mail ou localização física. No entanto, para fornecer a funcionalidade principal do jogo, recolhemos:",
          "UUID (Identificador Único Universal): um identificador não pessoal armazenado localmente e nos nossos servidores seguros para facilitar o restauro de compras consumíveis e acompanhar o progresso do jogo.",
          "Informações do dispositivo: dados técnicos básicos (por exemplo, modelo do dispositivo, versão do SO) necessários para a estabilidade da aplicação e monitorização de desempenho.",
        ],
      },
      {
        title: "3. Pagamentos e consumíveis",
        body: [
          "A nossa aplicação oferece compras na aplicação. Todas as transações financeiras são processadas de forma segura pelo Google Play Billing.",
          "Não temos acesso aos dados do seu cartão de crédito nem aos seus dados bancários.",
          "Utilizamos o seu UUID apenas para garantir que os itens comprados são corretamente atribuídos à sua conta.",
        ],
      },
      {
        title: "4. Publicidade",
        body: [
          "Para manter o nosso jogo gratuito, apresentamos anúncios. Estas redes de anúncios de terceiros (por exemplo, AdMob) podem utilizar identificadores pseudónimos para apresentar anúncios relevantes. Pode gerir as suas preferências de anúncios nas definições do seu dispositivo Android (Definições Google > Anúncios).",
        ],
      },
      {
        title: "5. Retenção de dados e segurança",
        body: [
          "Armazenamento local: o seu UUID e tokens de compra são armazenados localmente no seu dispositivo para garantir uma jogabilidade fluida sem uma ligação constante à internet.",
          "Segurança: implementamos medidas de segurança padrão da indústria para proteger a integridade dos dados UUID utilizados para o restauro de pagamentos.",
        ],
      },
      {
        title: "6. Privacidade das crianças",
        body: [
          "O nosso jogo foi concebido para um público geral. Não recolhemos conscientemente dados pessoais de crianças com menos de 13 anos. Se acreditar que recolhemos inadvertidamente tais informações, contacte-nos imediatamente para que possamos eliminá-las.",
        ],
      },
      {
        title: "7. Alterações a esta política",
        body: [
          "Podemos atualizar a nossa Política de Privacidade ocasionalmente. Iremos notificar quaisquer alterações ao publicar a nova Política de Privacidade nesta página.",
        ],
      },
      {
        title: "8. Contacte-nos",
        body: [
          "Se tiver alguma questão sobre esta Política de Privacidade, contacte-nos em:",
          "E-mail: gusak.lab@gmail.com",
        ],
      },
    ],
  },
};

export const dynamicParams = false;

export function generateStaticParams(): Array<{ locale: Locale }> {
  return LOCALES.map((locale) => ({ locale }));
}

export function generateMetadata({
  params,
}: {
  params: { locale: string };
}): Metadata {
  const { locale } = params;
  const l = (LOCALES.includes(locale as Locale) ? (locale as Locale) : "en-US") satisfies Locale;
  const policy = POLICIES[l];

  return {
    title: policy.pageTitle,
  };
}

export default async function PrivacyPolicyPage({
  params,
}: {
  params: { locale: string };
}) {
  const { locale } = params;
  const l = (LOCALES.includes(locale as Locale) ? (locale as Locale) : "en-US") satisfies Locale;
  const policy = POLICIES[l];

  return (
    <main className="min-h-screen bg-background">
      <div className="max-w-3xl mx-auto px-4 py-16">
        <h1 className="text-3xl md:text-4xl font-bold mb-3">{policy.pageTitle}</h1>
        <p className="text-gray-500 mb-10">
          {policy.lastUpdatedLabel} {policy.lastUpdatedValue}
        </p>

        <div className="space-y-10">
          {policy.sections.map((section) => (
            <section key={section.title}>
              <h2 className="text-xl md:text-2xl font-semibold mb-3">{section.title}</h2>
              <div className="space-y-3 text-gray-400 leading-relaxed">
                {section.body.map((p, idx) => (
                  <p key={idx}>{p}</p>
                ))}
              </div>
            </section>
          ))}
        </div>
      </div>
    </main>
  );
}
