"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const assert = require("assert");
const vscode = require("vscode");
// import * as myExtension from '../../extension';
suite('Extension Test Suite', () => {
    vscode.window.showInformationMessage('Start all tests.');
    test('Sample test', () => {
        assert.strictEqual([1, 2, 3].indexOf(5), -1);
        assert.strictEqual([1, 2, 3].indexOf(0), -1);
    });
});
//# sourceMappingURL=extension.text.js.map