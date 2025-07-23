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
- Export en **JSON**, **texte** ou **HTML**
- Interface **CLI intuitive**
- Serveur **Web RESTful** avec une **interface Web interactive**
- Architecture propre, testable, modulaire (Clean Architecture)

## 📐 Architecture

Le projet suit les principes de la **Clean Architecture** :

```bash
src/
├── core/           # Logique métier (use cases, entités)
├── application/    # Services d'application (interfaces, orchestrations)
├── infrastructure/ # Accès réseau, implémentations HTTP
└── adapters/       # Interfaces CLI et Web (Javalin)
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
* Lance un serveur RESTful + une interface web
* --port (optionnel) : Port d'écoute (par défaut 8081)
* Accès à l'interface Web : http://localhost:8081
* API de scan disponible via :
```bash
curl "http://localhost:8081/scan?url=https://example.com&format=json"
```

## 🖥️ Interface Web
Une interface HTML développée avec soin, qui permet de :
- Renseigner l’URL à scanner
- Choisir dynamiquement le format du rapport
- Lancer le scan via le navigateur
- Voir les résultats instantanément

## 💡 À venir
- Interface Web avec historique des scans
- Intégration CI/CD via GitHub Actions
- Support HTTP/2 et HTTP/3

## 🔓 Licence
✍️ Développé avec ❤️ par Moukhafi Anass pour la communauté open-source · 2025

"N’hésitez pas à contribuer ou à faire remonter vos idées et bugs via les issues !"