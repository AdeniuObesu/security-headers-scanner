# 🛡️ Security Headers Scanner

Un outil open-source d’analyse des en-têtes de sécurité HTTP, conçu pour identifier les mauvaises configurations de sécurité sur les sites web. Développé avec une architecture propre et maintenable selon les principes de Clean Architecture (Uncle Bob).

## 🚀 Objectif

Fournir un scanner léger et extensible permettant d’évaluer la conformité des en-têtes HTTP d’un site web aux meilleures pratiques de sécurité (OWASP, Mozilla Observatory, etc.).

## 🔍 Fonctionnalités

- Analyse des en-têtes suivants :
  - `Strict-Transport-Security`
  - `Content-Security-Policy`
  - `X-Content-Type-Options`
  - `X-Frame-Options`
  - `X-XSS-Protection`
  - `Referrer-Policy`
  - `Permissions-Policy`
- Rapport clair des en-têtes manquants, faibles ou mal configurés
- Export JSON ou texte
- Interface CLI intuitive
- Architecture propre, testable, modulaire

## 📐 Architecture

Le projet suit les principes de la **Clean Architecture** :

```bash
src/
├── core/ # Logique métier (use cases, entités)
├── application/ # Services d'application (interfaces, orchestrations)
├── infrastructure/ # Accès réseau, implémentations HTTP
└── adapters/ # Interfaces CLI, Web (à venir)
```


✅ Tests unitaires & intégration  
✅ Respect du principe de séparation des préoccupations  
✅ Couplage faible et testabilité forte

## 🛠️ Utilisation

```bash
git clone https://github.com/adeniuobesu/security-headers-scanner.git
cd security-headers-scanner
./scanner

--url        URL à scanner
--output     Chemin du répertoire de sortie
--format     Format du rapport (json|text|html)
```

## 📦 Exemples de sorte
```bash
[!] X-Frame-Options: absent
[!] Content-Security-Policy: absent
[✓] Strict-Transport-Security: présent
```

## 💡 À venir
- Interface Web minimale (vue des résultats)
- Intégration CI/CD GitHub Actions
- Suggestions automatiques de correction
- Support pour HTTP/2

