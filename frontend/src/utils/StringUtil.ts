const REGEX_SOMENTE_NUMERO = /[^0-9]/g;

const aplicarMascaraCpfCNPJ = (valor: string) => {
    const digitos = (valor || '').replace(REGEX_SOMENTE_NUMERO, '');
    if (digitos.length === 11) return `${digitos.slice(0, 3)}.${digitos.slice(3, 6)}.${digitos.slice(6, 9)}-${digitos.substring(9)}`;
    if (digitos.length === 14) return `${digitos.slice(0, 2)}.${digitos.slice(2, 5)}.${digitos.slice(5, 8)}/${digitos.slice(8, 12)}-${digitos.substring(12)}`;
    return '';
};

const jsonToBase64 = (jsonObject: any) => btoa(unescape(encodeURIComponent(JSON.stringify(jsonObject))));
const base64ToJson = (base64: string) => JSON.parse(decodeURIComponent(escape(window.atob(base64))));

export { aplicarMascaraCpfCNPJ, jsonToBase64, base64ToJson };
