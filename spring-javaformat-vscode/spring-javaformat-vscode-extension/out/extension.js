"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.deactivate = exports.activate = void 0;
const vscode = require("vscode");
const SpringDocumentFormattingEditProvider_1 = require("./SpringDocumentFormattingEditProvider");
function activate(context) {
    console.log('Activated spring-javaformat extension');
    context.subscriptions.push(vscode.languages.registerDocumentFormattingEditProvider([
        {
            language: 'java',
            scheme: 'file',
        },
    ], new SpringDocumentFormattingEditProvider_1.default()));
}
exports.activate = activate;
function deactivate() { }
exports.deactivate = deactivate;
//# sourceMappingURL=extension.js.map