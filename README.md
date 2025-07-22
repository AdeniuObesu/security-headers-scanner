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
- Export en JSON, texte ou HTML
- Interface CLI intuitive
- Serveur Web RESTful pour lancer des scans via HTTP
- Architecture propre, testable, modulaire

## 📐 Architecture

Le projet suit les principes de la **Clean Architecture** :

```bash
src/
├── core/          # Logique métier (use cases, entités)
├── application/   # Services d'application (interfaces, orchestrations)
├── infrastructure/# Accès réseau, implémentations HTTP
└── adapters/      # Interfaces CLI et Web (Javalin)
```

✅ Tests unitaires & intégration
✅ Respect du principe de séparation des préoccupations
✅ Couplage faible et testabilité forte

## 🛠️ Utilisation

### En mode CLI
```bash
./scanner --url <url> --format <json|text|html> [--output <répertoire>]
```

* --url : URL du site à scanner
* --format : Format de sortie du rapport (JSON, TEXT ou HTML)
* --output (optionnel) : Répertoire dans lequel sauvegarder le rapport
### En mode Serveur Web
```bash
./scanner --web [--port <port>]
```

* Lance un serveur web RESTful écoutant par défaut sur le port 8081
* Option --port permet de changer le port d’écoute (ex: 9090)
* API disponible sur http://localhost:<port>/scan?url=<url>&format=<format>

```bash
curl "http://localhost:8081/scan?url=https://example.com&format=json"
```

## 💡 À venir
- Interface Web minimale pour visualiser les résultats en temps réel
- Intégration CI/CD via GitHub Actions
- Suggestions automatiques de corrections des headers
- Support HTTP/2 et HTTP/3

"N’hésitez pas à contribuer ou à faire remonter vos idées et bugs via les issues !"