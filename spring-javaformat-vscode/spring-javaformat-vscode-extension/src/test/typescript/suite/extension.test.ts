import * as assert from 'assert';
import * as vscode from 'vscode';
import { after, before } from 'mocha';
import * as extension from '../../../main/typescript/SpringDocumentFormattingEditProvider'

suite('Extension Test Suite', () => {
	test('Sample test', () => {
		assert.strictEqual(-1, [1, 2, 3].indexOf(5));
		assert.strictEqual(-1, [1, 2, 3].indexOf(0));
	});
});
